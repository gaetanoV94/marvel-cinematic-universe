package com.example.mcu.service;

import static com.example.mcu.specification.MovieSpecifications.hasBudgetLessThan;
import static com.example.mcu.specification.MovieSpecifications.hasDirector;
import static com.example.mcu.specification.MovieSpecifications.hasGrossGreaterThan;
import static com.example.mcu.specification.MovieSpecifications.hasMetacriticMarkGreaterThan;
import static com.example.mcu.specification.MovieSpecifications.hasRunningTimeBetween;
import static com.example.mcu.specification.MovieSpecifications.hasTitle;
import static com.example.mcu.specification.MovieSpecifications.isInPhase;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.mcu.dto.MovieRecord;
import com.example.mcu.entity.Actor;
import com.example.mcu.entity.Cast;
import com.example.mcu.entity.MCUPhase;
import com.example.mcu.entity.Movie;
import com.example.mcu.exception.PhaseNotFoundException;
import com.example.mcu.repository.MCUPhaseRepository;
import com.example.mcu.repository.MovieRepository;

import jakarta.transaction.Transactional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MCUPhaseRepository phaseRepository;
    private final ActorService actorService;

    public MovieService(MovieRepository movieRepository, MCUPhaseRepository phaseRepository, ActorService actorService) {
        this.movieRepository = movieRepository;
        this.phaseRepository = phaseRepository;
        this.actorService = actorService;
    }

    @Transactional
    public Movie saveMovie(MovieRecord record) {
        MCUPhase phase = phaseRepository.findByName(record.phaseName())
                .orElseThrow(() -> new PhaseNotFoundException("MCU Phase not found: " + record.phaseName()));

        Movie movie = new Movie();
        movie.setTitle(record.title());
        movie.setDirector(record.director());
        movie.setBudget(record.budget());
        movie.setMetacriticMark(record.metacriticMark());
        movie.setRunningTime(record.runningTime());
        movie.setGross(record.gross());
        movie.setReleaseYear(record.releaseYear());
        movie.setPhase(phase);

        List<Cast> castList = record.cast().stream().map(castRecord -> {
            Actor actor = actorService.findOrCreate(
                    castRecord.actor().name(),
                    castRecord.actor().surname()
            );
            Cast cast = new Cast();
            cast.setActor(actor);
            cast.setProduct(movie);
            cast.setCharacter(castRecord.character());
            return cast;
        }).toList();

        movie.setCast(castList);
        return movieRepository.save(movie);
    }

    public List<Movie> filterMovies(String title, String director, Long grossMin, Integer durationMin,
                                    Integer durationMax, Long budgetMax, Double metacriticMin, String phase) {
        Specification<Movie> spec = Specification
                .where(hasTitle(title))
                .and(hasDirector(director))
                .and(hasGrossGreaterThan(grossMin))
                .and(hasRunningTimeBetween(durationMin, durationMax))
                .and(hasBudgetLessThan(budgetMax))
                .and(hasMetacriticMarkGreaterThan(metacriticMin))
                .and(isInPhase(phase));

        return movieRepository.findAll(spec);
    }
}
