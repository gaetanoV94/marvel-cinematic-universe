package com.example.mcu.controller;

import com.example.mcu.dto.MovieRecord;
import com.example.mcu.entity.Movie;
import com.example.mcu.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody MovieRecord record) {
        Movie saved = movieService.saveMovie(record);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/filter")
    public List<Movie> filter(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String director,
        @RequestParam(required = false) Long grossMin,
        @RequestParam(required = false) Integer durationMin,
        @RequestParam(required = false) Integer durationMax,
        @RequestParam(required = false) Long budgetMax,
        @RequestParam(required = false) Double metacriticMin,
        @RequestParam(required = false) String phase
    ) {
        return movieService.filterMovies(title, director, grossMin, durationMin, durationMax, budgetMax, metacriticMin, phase);
    }
}
