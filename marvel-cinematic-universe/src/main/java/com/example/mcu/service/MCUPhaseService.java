package com.example.mcu.service;

import java.util.List;

import com.example.mcu.entity.MCUPhase;

public interface MCUPhaseService {
    MCUPhase saveFase(MCUPhase fase);
    List<MCUPhase> getAllFasi();
    MCUPhase getFaseByNome(String nome);
}
