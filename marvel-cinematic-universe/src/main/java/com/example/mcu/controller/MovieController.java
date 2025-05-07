package com.example.mcu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mcu.dto.MovieRecord;
import com.example.mcu.entity.Movie;
import com.example.mcu.service.MovieService;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/create-new-movie")
    public ResponseEntity<Movie> saveMovie(@RequestBody MovieRecord filmRecord) {
    	return Optional.ofNullable(movieService.saveMovie(filmRecord))
    			.map(newMovie -> ResponseEntity.status(HttpStatus.CREATED).body(newMovie))
    			.orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/get-all-films")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
		return new ResponseEntity<List<Movie>>(movies.stream().toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
    	return Optional.ofNullable(movieService.getMovieById(id))
    			.map(ResponseEntity::ok)
    			.orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<Movie>> searchByTitle(@RequestParam String title) {
    	return Optional.ofNullable(movieService.findByTitle(title))
    			.map(ResponseEntity::ok)
    			.orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/search/director")
    public ResponseEntity<List<Movie>> searchByDirector(@RequestParam String director) {
    	return Optional.ofNullable(movieService.findByDirector(director))
		.map(ResponseEntity::ok)
		.orElseGet(() -> ResponseEntity.badRequest().build());
    }
    
    @GetMapping("/search/gross")
    public ResponseEntity<List<Movie>> findByGrossGreaterThan(@RequestParam double gross) {
    	return Optional.ofNullable(movieService.findByGrossGreaterThan(gross))
		.map(ResponseEntity::ok)
		.orElseGet(() -> ResponseEntity.badRequest().build());
    }
    
    @GetMapping("/search/running-time")
    public ResponseEntity<List<Movie>> findByRunningTimeBetween(@RequestParam int minRunningTime, @RequestParam int maxRunningTime) {
    	return Optional.ofNullable(movieService.findByRunningTimeBetween(minRunningTime, maxRunningTime))
		.map(ResponseEntity::ok)
		.orElseGet(() -> ResponseEntity.badRequest().build());
    }
    
    @GetMapping("/search/budget")
    public ResponseEntity<List<Movie>> findByBudgetLessThan(@RequestParam double budget) {
    	return Optional.ofNullable(movieService.findByBudgetLessThan(budget))
		.map(ResponseEntity::ok)
		.orElseGet(() -> ResponseEntity.badRequest().build());
    }
    
    @GetMapping("/search/metacritic")
    public ResponseEntity<List<Movie>> findByMetacriticMarkGreaterThan(@RequestParam double metacriticMark) {
    	return Optional.ofNullable(movieService.findByMetacriticMarkGreaterThan(metacriticMark))
		.map(ResponseEntity::ok)
		.orElseGet(() -> ResponseEntity.badRequest().build());
    }
    
    @GetMapping("/search/phasename")
    public ResponseEntity<List<Movie>> findByPhaseName(@RequestParam String phaseName) {
    	return Optional.ofNullable(movieService.findByPhaseName(phaseName))
		.map(ResponseEntity::ok)
		.orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
