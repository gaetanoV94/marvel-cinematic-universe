package com.example.mcu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mcu.dto.TVShowRecord;
import com.example.mcu.entity.TVShow;
import com.example.mcu.service.TVShowService;

@RestController
@RequestMapping("/api/tvshows")
public class TVShowController {

    private final TVShowService tvShowService;

    public TVShowController(TVShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @PostMapping
    public ResponseEntity<TVShow> createTVShow(@RequestBody TVShowRecord record) {
        TVShow saved = tvShowService.saveTVShow(record);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/filter")
    public List<TVShow> filter(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String director,
        @RequestParam(required = false) String platform,
        @RequestParam(required = false) Long budgetMax,
        @RequestParam(required = false) Integer minEpisodes,
        @RequestParam(required = false) Double metacriticMin,
        @RequestParam(required = false) String phase
    ) {
        return tvShowService.filterTVShows(title, director, platform, budgetMax, minEpisodes, metacriticMin, phase);
    }
}

