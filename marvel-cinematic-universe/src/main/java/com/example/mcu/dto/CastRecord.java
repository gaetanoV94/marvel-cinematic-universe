package com.example.mcu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CastRecord(
		Long id,
		@JsonProperty("actor") ActorRecord actor,
		@JsonProperty("character") String character
		) {

}
