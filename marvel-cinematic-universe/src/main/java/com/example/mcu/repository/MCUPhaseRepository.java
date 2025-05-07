package com.example.mcu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mcu.entity.MCUPhase;

@Repository
public interface MCUPhaseRepository extends JpaRepository<MCUPhase, Long> {
    MCUPhase findByName(String nome);
}
