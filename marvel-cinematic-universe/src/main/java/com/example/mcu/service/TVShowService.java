package com.example.mcu.service;

import java.util.List;

import com.example.mcu.dto.TVShowRecord;
import com.example.mcu.entity.TVShow;

public interface TVShowService {
	TVShow saveTvShow(TVShowRecord serieTv);
    List<TVShow> getAllTVShows();
    List<TVShow> findByTitle(String titolo);
    List<TVShow> findByDirector(String director);
    List<TVShow> findByPlatform(String piattaforma);
    List<TVShow> findByBudgetLessThan(double budget);
    List<TVShow> findByNumberOfEpisodesGreaterThan(int numberOfEpisodes);
    List<TVShow> findByMetacriticMarkGreaterThan(double metacriticMark);
    List<TVShow> findByPhaseName(String faseNome);
}
