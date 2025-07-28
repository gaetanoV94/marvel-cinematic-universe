package com.example.mcu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mcu.entity.Cast;
import com.example.mcu.service.CastService;

@RestController
@RequestMapping("/api/cast")
public class CastController {

    private final CastService castService;

    public CastController(CastService castService) {
        this.castService = castService;
    }

    @GetMapping("/filter")
    public List<Cast> filter(
        @RequestParam(required = false) String actorName,
        @RequestParam(required = false) String actorSurname,
        @RequestParam(required = false) String character,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String phase
    ) {
        return castService.filterCast(actorName, actorSurname, character, title, phase);
    }
}

