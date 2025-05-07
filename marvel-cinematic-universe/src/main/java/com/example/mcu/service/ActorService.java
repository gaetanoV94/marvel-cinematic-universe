package com.example.mcu.service;

import java.util.List;

import com.example.mcu.entity.Actor;

public interface ActorService {
    Actor saveActor(Actor attore);
    List<Actor> getAllAttori();
    List<Object[]> getAttoriByNumeroDiApparizioni();
}
