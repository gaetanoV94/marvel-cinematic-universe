package com.example.mcu.dto;

import java.util.List;

public record MovieRecord(
    String title,
    int releaseYear,
    String director,
    String platform,
    long budget,
    double metacriticMark,
    int runningTime,
    long gross,
    String phaseName,
    List<CastRecord> cast
) {}
