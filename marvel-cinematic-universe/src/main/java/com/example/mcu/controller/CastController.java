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

	@Autowired
    private final CastService castService;

    @Autowired
    public CastController(CastService castService) {
        this.castService = castService;
    }

    @PostMapping("/save-new-cast")
    public Cast saveCast(@RequestBody Cast cast) {
        return castService.saveCast(cast);
    }

    @GetMapping("/get-cast")
    public List<Cast> getAllCast() {
        return castService.getAllCast();
    }

    @GetMapping("/search/character")
    public List<Cast> findByCharacter(@RequestParam String character) {
        return castService.findByCharacter(character);
    }
}
