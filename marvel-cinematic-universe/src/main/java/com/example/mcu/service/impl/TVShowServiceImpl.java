package com.example.mcu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcu.dto.CastRecord;
import com.example.mcu.dto.TVShowRecord;
import com.example.mcu.entity.Actor;
import com.example.mcu.entity.Cast;
import com.example.mcu.entity.MCUPhase;
import com.example.mcu.entity.TVShow;
import com.example.mcu.repository.ActorRepository;
import com.example.mcu.repository.CastRepository;
import com.example.mcu.repository.MCUPhaseRepository;
import com.example.mcu.repository.TVShowRepository;
import com.example.mcu.service.TVShowService;
import com.example.mcu.service.exception.PhaseNotFoundException;

@Service
public class TVShowServiceImpl implements TVShowService {
	
    @Autowired
    private final TVShowRepository tvShowRepository;
    
    @Autowired
    private final MCUPhaseRepository mcuPhaseRepository;
    
    @Autowired
	private final CastRepository castRepository;
	
	@Autowired
	private final ActorRepository actorRepository;
    
    @Autowired
    public TVShowServiceImpl(TVShowRepository tvShowRepository, 
    		MCUPhaseRepository mcuPhaseRepository, CastRepository castRepository,
    		ActorRepository actorRepository) {
        this.tvShowRepository = tvShowRepository;
        this.mcuPhaseRepository = mcuPhaseRepository;
        this.castRepository = castRepository;
        this.actorRepository = actorRepository;
    }
    
    @Override
	public TVShow saveTvShow(TVShowRecord tvShowRecord) {
		MCUPhase phase = Optional.ofNullable(mcuPhaseRepository.findByName(tvShowRecord.phaseName()))
				.orElseThrow(() -> new PhaseNotFoundException("MCU Phase not found: " + tvShowRecord.phaseName()));
		
        TVShow tvShow = new TVShow();
        tvShow.setTitle(tvShowRecord.title());
        tvShow.setDirector(tvShowRecord.director());
        tvShow.setBudget(tvShowRecord.budget());
        tvShow.setMetacriticMark(tvShowRecord.metacriticMark());
        tvShow.setNumberOfSeasons(tvShowRecord.numberOfSeasons());
        tvShow.setPhase(phase);
        tvShow.setPlatform(tvShowRecord.platform());
        tvShow.setReleaseYear(tvShowRecord.releaseYear());
        
        // Gestione del cast
        for (CastRecord castRecord : tvShowRecord.cast()) {
            Actor actor = actorRepository
                .findByNomeContainingOrCognomeContaining(castRecord.actor().name(), castRecord.actor().surname())
                .stream()
                .findFirst()
                .orElse(null);

            if (actor == null) {
                // Creiamo un nuovo attore se non esiste già
            	actor = new Actor();
            	actor.setName(castRecord.actor().name());
            	actor.setSurname(castRecord.actor().surname());
            	actor = actorRepository.save(actor);
            }

            // Creazione della relazione Cast
            Cast cast = new Cast();
            cast.setActor(actor);
            cast.setProduct(tvShow);
            cast.setCharacter(castRecord.character());

            castRepository.save(cast); // Salva il cast
        }

        return tvShowRepository.save(tvShow);
	}
   

    @Override
    public List<TVShow> getAllTVShows() {
        return tvShowRepository
        		.findAll()
        		.stream()
                .toList();
    }

    @Override
    public List<TVShow> findByTitle(String titolo) {
        return tvShowRepository.findByTitle(titolo).stream().toList();
    }

    @Override
    public List<TVShow> findByPlatform(String piattaforma) {
        return tvShowRepository.findByPlatform(piattaforma).stream().toList();
    }

    @Override
    public List<TVShow> findByPhaseName(String faseNome) {
        return tvShowRepository.findByPhaseName(faseNome)
        		.stream()
        		.toList();
    }

	@Override
	public List<TVShow> findByBudgetLessThan(double budget) {
		return tvShowRepository.findByBudgetLessThan(budget).stream().toList();
	}

	@Override
	public List<TVShow> findByNumberOfEpisodesGreaterThan(int numberOfEpisodes) {
		return tvShowRepository.findByNumberOfEpisodesGreaterThan(numberOfEpisodes).stream().toList();
	}

	@Override
	public List<TVShow> findByMetacriticMarkGreaterThan(double metacriticMark) {
		return tvShowRepository.findByMetacriticMarkGreaterThan(metacriticMark).stream().toList();
	}

	@Override
	public List<TVShow> findByDirector(String director) {
		return tvShowRepository.findByDirector(director).stream().toList();
	}
}

