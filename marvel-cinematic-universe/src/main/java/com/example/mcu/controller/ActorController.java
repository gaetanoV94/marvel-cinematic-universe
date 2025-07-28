package com.example.mcu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mcu.entity.Actor;
import com.example.mcu.service.ActorService;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/filter")
    public List<Actor> filter(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String surname
    ) {
        return actorService.filterActors(name, surname);
    }
}
