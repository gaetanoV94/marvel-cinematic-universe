package com.example.mcu.dto;

import java.util.List;

public record TVShowRecord(
    String title,
    int releaseYear,
    String director,
    String platform,
    long budget,
    double metacriticMark,
    int numberOfSeasons,
    int numberOfEpisodes,
    String phaseName,
    List<CastRecord> cast
) {}
