package com.example.mcu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mcu.entity.Actor;
import com.example.mcu.service.ActorService;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

	
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    public Actor saveActor(@RequestBody Actor attore) {
        return actorService.saveActor(attore);
    }

    @GetMapping
    public List<Actor> getAllAttori() {
        return actorService.getAllAttori();
    }

    @GetMapping("/top")
    public List<Object[]> getAttoriByNumeroDiApparizioni() {
        return actorService.getAttoriByNumeroDiApparizioni();
    }
}
