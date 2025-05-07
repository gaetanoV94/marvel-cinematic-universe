package com.example.mcu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ActorRecord(
		Long id,
		@JsonProperty("name") String name,
		@JsonProperty("surname") String surname
		) {

}
