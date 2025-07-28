package com.example.mcu.controller;

import com.example.mcu.entity.MCUPhase;
import com.example.mcu.service.MCUPhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phases")
public class MCUPhaseController {

    private final MCUPhaseService phaseService;

    public MCUPhaseController(MCUPhaseService phaseService) {
        this.phaseService = phaseService;
    }

    @GetMapping
    public List<MCUPhase> getAll() {
        return phaseService.getAllPhases();
    }

    @PostMapping
    public ResponseEntity<MCUPhase> create(@RequestBody MCUPhase phase) {
        return ResponseEntity.status(201).body(phaseService.createPhase(phase));
    }

    @GetMapping("/{name}")
    public MCUPhase getByName(@PathVariable String name) {
        return phaseService.getPhaseByName(name);
    }
}
