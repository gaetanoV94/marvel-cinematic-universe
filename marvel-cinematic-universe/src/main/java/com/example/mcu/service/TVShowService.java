package com.example.mcu.service;

import com.example.mcu.dto.TVShowRecord;
import com.example.mcu.entity.*;
import com.example.mcu.exception.PhaseNotFoundException;
import com.example.mcu.repository.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

import static com.example.mcu.specification.TVShowSpecifications.*;

@Service
public class TVShowService {

    private final TVShowRepository tvShowRepository;
    private final MCUPhaseRepository phaseRepository;
    private final ActorService actorService;

    public TVShowService(TVShowRepository tvShowRepository, MCUPhaseRepository phaseRepository, ActorService actorService) {
        this.tvShowRepository = tvShowRepository;
        this.phaseRepository = phaseRepository;
        this.actorService = actorService;
    }

    @Transactional
    public TVShow saveTVShow(TVShowRecord record) {
        MCUPhase phase = phaseRepository.findByName(record.phaseName())
                .orElseThrow(() -> new PhaseNotFoundException("MCU Phase not found: " + record.phaseName()));

        TVShow show = new TVShow();
        show.setTitle(record.title());
        show.setDirector(record.director());
        show.setBudget(record.budget());
        show.setMetacriticMark(record.metacriticMark());
        show.setNumberOfSeasons(record.numberOfSeasons());
        show.setNumberOfEpisodes(record.numberOfEpisodes());
        show.setReleaseYear(record.releaseYear());
        show.setPlatform(record.platform());
        show.setPhase(phase);

        List<Cast> castList = record.cast().stream().map(castRecord -> {
            Actor actor = actorService.findOrCreate(
                    castRecord.actor().name(),
                    castRecord.actor().surname()
            );
            Cast cast = new Cast();
            cast.setActor(actor);
            cast.setProduct(show);
            cast.setCharacter(castRecord.character());
            return cast;
        }).toList();

        show.setCast(castList);
        return tvShowRepository.save(show);
    }

    public List<TVShow> filterTVShows(String title, String director, String platform, Long budgetMax,
                                      Integer minEpisodes, Double metacriticMin, String phase) {
        Specification<TVShow> spec = Specification
                .where(hasTitle(title))
                .and(hasDirector(director))
                .and(hasPlatform(platform))
                .and(hasBudgetLessThan(budgetMax))
                .and(hasEpisodesGreaterThan(minEpisodes))
                .and(hasMetacriticMarkGreaterThan(metacriticMin))
                .and(isInPhase(phase));

        return tvShowRepository.findAll(spec);
    }
}
