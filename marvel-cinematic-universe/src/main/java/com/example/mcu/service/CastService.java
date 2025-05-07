package com.example.mcu.service;

import java.util.List;

import com.example.mcu.entity.Cast;

public interface CastService {
    Cast saveCast(Cast cast);
    List<Cast> getAllCast();
    List<Cast> findByCharacter(String character);
    List<Cast> findCastByActorFullName(String name, String surname);
    List<Cast> findCastByActorNameOrSurname(String nameOrSurname);
    List<Cast> findCastByTitle(String title);
    List<Cast> findCastByTitleLike(String title);
}
