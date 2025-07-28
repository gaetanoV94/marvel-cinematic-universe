package com.example.mcu.service;

import com.example.mcu.entity.MCUPhase;
import com.example.mcu.exception.PhaseNotFoundException;
import com.example.mcu.repository.MCUPhaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MCUPhaseService {

    private final MCUPhaseRepository phaseRepository;

    public MCUPhaseService(MCUPhaseRepository phaseRepository) {
        this.phaseRepository = phaseRepository;
    }

    public MCUPhase getPhaseByName(String name) {
        return phaseRepository.findByName(name)
                .orElseThrow(() -> new PhaseNotFoundException("MCU Phase not found: " + name));
    }

    public List<MCUPhase> getAllPhases() {
        return phaseRepository.findAll();
    }

    public MCUPhase createPhase(MCUPhase phase) {
        return phaseRepository.save(phase);
    }
}
