package com.example.mcu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcu.dto.CastRecord;
import com.example.mcu.dto.MovieRecord;
import com.example.mcu.entity.Actor;
import com.example.mcu.entity.Cast;
import com.example.mcu.entity.MCUPhase;
import com.example.mcu.entity.Movie;
import com.example.mcu.repository.ActorRepository;
import com.example.mcu.repository.CastRepository;
import com.example.mcu.repository.MCUPhaseRepository;
import com.example.mcu.repository.MovieRepository;
import com.example.mcu.service.MovieService;
import com.example.mcu.service.exception.MovieNotFoundException;
import com.example.mcu.service.exception.PhaseNotFoundException;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
    private final MovieRepository movieRepository;
	
	@Autowired
    private final MCUPhaseRepository mcuPhaseRepository;
	
	@Autowired
	private final CastRepository castRepository;
	
	@Autowired
	private final ActorRepository actorRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, 
    		MCUPhaseRepository mcuPhaseRepository, CastRepository castRepository,
    		ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.mcuPhaseRepository = mcuPhaseRepository;
        this.castRepository = castRepository;
        this.actorRepository = actorRepository;
    }

    @Override
    public Movie saveMovie(MovieRecord filmRecord) {
    	
    	MCUPhase phase = Optional.ofNullable(mcuPhaseRepository.findByName(filmRecord.phaseName()))
    			.orElseThrow(() -> 
    				new PhaseNotFoundException("MCU Phase not found: " + filmRecord.phaseName())); 
    	
        Movie movie = new Movie();
        movie.setTitle(filmRecord.title());
        movie.setDirector(filmRecord.director());
        movie.setBudget(filmRecord.budget());
        movie.setMetacriticMark(filmRecord.metacriticMark());
        movie.setRunningTime(filmRecord.runningTime());
        movie.setGross(filmRecord.gross());
        movie.setReleaseYear(filmRecord.releaseYear());
        movie.setPhase(phase);
        
        // Gestione del cast
        for (CastRecord castRecord : filmRecord.cast()) {
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
            cast.setProduct(movie);
            cast.setCharacter(castRecord.character());

            castRepository.save(cast); // Salva il cast
        }

        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository
        		.findAll()
        		.stream()
        		.toList();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> 
                new MovieNotFoundException("MCU movie with id " + id + " is not in the database"));
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title)
        		.stream()
                .toList();
    }

    @Override
    public List<Movie> findByDirector(String director) {
        return movieRepository.findByDirector(director)
        		.stream()
        		.toList();
    }

    @Override
    public List<Movie> findByGrossGreaterThan(double gross) {
        return movieRepository.findByGrossGreaterThan(gross)
        		.stream()
        		.toList();
    }

    @Override
    public List<Movie> findByRunningTimeBetween(int minDurata, int maxDurata) {
        return movieRepository.findByRunningTimeBetween(minDurata, maxDurata)
        		.stream()
                .toList();
    }

    @Override
    public List<Movie> findByBudgetLessThan(double budget) {
        return movieRepository.findByBudgetLessThan(budget)
        		.stream()
                .toList();
    }

    @Override
    public List<Movie> findByMetacriticMarkGreaterThan(double rating) {
        return movieRepository.findByMetacriticMarkGreaterThan(rating)
        		.stream()
                .toList();
    }

    @Override
    public List<Movie> findByPhaseName(String faseNome) {
        return movieRepository.findByPhaseName(faseNome)
        		.stream()
        		.toList();
    }
}
