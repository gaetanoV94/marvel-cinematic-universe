package com.example.mcu.controller;

import com.example.mcu.entity.MCUPhase;
import com.example.mcu.service.MCUPhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fasi")
public class MCUPhaseController {

    private final MCUPhaseService faseMCUService;

    @Autowired
    public MCUPhaseController(MCUPhaseService faseMCUService) {
        this.faseMCUService = faseMCUService;
    }

    @PostMapping
    public MCUPhase saveFase(@RequestBody MCUPhase fase) {
        return faseMCUService.saveFase(fase);
    }

    @GetMapping("/get-all-fasi")
    public List<MCUPhase> getAllFasi() {
        return faseMCUService.getAllFasi();
    }

    @GetMapping("/search")
    public MCUPhase getFaseByNome(@RequestParam String nome) {
        return faseMCUService.getFaseByNome(nome);
    }
}
