package com.example.mcu.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TVShowRecord(
		Long id,
		@JsonProperty("title") String title,
		@JsonProperty("releaseYear") int releaseYear,
		@JsonProperty("director") String director,
		@JsonProperty("budget") long budget,
		@JsonProperty("metacriticMark") double metacriticMark,
		@JsonProperty("platform") String platform,
		@JsonProperty("numberOfSeasons") int numberOfSeasons,
		@JsonProperty("phaseName") String phaseName,
		@JsonProperty("cast") List<CastRecord> cast
		) {

}
