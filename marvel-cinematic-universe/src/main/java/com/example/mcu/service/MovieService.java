package com.example.mcu.service;

import java.util.List;

import com.example.mcu.dto.MovieRecord;
import com.example.mcu.entity.Movie;

public interface MovieService {
	Movie saveMovie(MovieRecord film);
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    List<Movie> findByTitle(String titolo);
    List<Movie> findByDirector(String regista);
    List<Movie> findByGrossGreaterThan(double incasso);
    List<Movie> findByRunningTimeBetween(int minDurata, int maxDurata);
    List<Movie> findByBudgetLessThan(double budget);
    List<Movie> findByMetacriticMarkGreaterThan(double rating);
    List<Movie> findByPhaseName(String faseNome);
}
