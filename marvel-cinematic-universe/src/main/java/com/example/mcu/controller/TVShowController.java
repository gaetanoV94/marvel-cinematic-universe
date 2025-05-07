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
	
	@Autowired
	public TVShowController(TVShowService tvShowService) {
		this.tvShowService = tvShowService;
	}
	
	@PostMapping("/create-new-tvshow")
	public ResponseEntity<TVShow> saveTvShow(@RequestBody TVShowRecord tvShowRecord){
		return Optional.ofNullable(tvShowService.saveTvShow(tvShowRecord))
				.map(newTvShow -> ResponseEntity.status(HttpStatus.CREATED).body(newTvShow))
				.orElseGet(() -> ResponseEntity.badRequest().build());
	}
	
	@GetMapping("/get-all-tvshows")
	public ResponseEntity<List<TVShow>> getAllTVShows(){
		List<TVShow> tvShows = tvShowService.getAllTVShows();
		return new ResponseEntity<List<TVShow>>(tvShows.stream().toList(), HttpStatus.OK);
	}
	
	@GetMapping("/search/title")
	public ResponseEntity<List<TVShow>> findByTitle(@RequestParam String title){
		return Optional.ofNullable(tvShowService.findByTitle(title))
    			.map(ResponseEntity::ok)
    			.orElseGet(() -> ResponseEntity.badRequest().build());
	}
	
	@GetMapping("/search/director")
    public ResponseEntity<List<TVShow>> findByDirector(@RequestParam String director) {
    	return Optional.ofNullable(tvShowService.findByDirector(director))
		.map(ResponseEntity::ok)
		.orElseGet(() -> ResponseEntity.badRequest().build());
    }
	
	@GetMapping("/search/platform")
    public ResponseEntity<List<TVShow>> findByPlatform(@RequestParam String director) {
    	return Optional.ofNullable(tvShowService.findByPlatform(director))
		.map(ResponseEntity::ok)
		.orElseGet(() -> ResponseEntity.badRequest().build());
    }
	
	@GetMapping("/search/budget")
    public ResponseEntity<List<TVShow>> findByBudgetLessThan(@RequestParam double budget) {
    	return Optional.ofNullable(tvShowService.findByBudgetLessThan(budget))
		.map(ResponseEntity::ok)
		.orElseGet(() -> ResponseEntity.badRequest().build());
    }
	
	@GetMapping("/search/platform")
    public ResponseEntity<List<TVShow>> findByNumberOfEpisodesGreaterThan(@RequestParam int numberOfEpisodes) {
    	return Optional.ofNullable(tvShowService.findByNumberOfEpisodesGreaterThan(numberOfEpisodes))
		.map(ResponseEntity::ok)
		.orElseGet(() -> ResponseEntity.badRequest().build());
    }
	
	@GetMapping("/search/metacritic")
    public ResponseEntity<List<TVShow>> findByMetacriticMarkGreaterThan(@RequestParam double metacriticMark) {
    	return Optional.ofNullable(tvShowService.findByMetacriticMarkGreaterThan(metacriticMark))
		.map(ResponseEntity::ok)
		.orElseGet(() -> ResponseEntity.badRequest().build());
    }
    
    @GetMapping("/search/phasename")
    public ResponseEntity<List<TVShow>> findByPhaseName(@RequestParam String phaseName) {
    	return Optional.ofNullable(tvShowService.findByPhaseName(phaseName))
		.map(ResponseEntity::ok)
		.orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
