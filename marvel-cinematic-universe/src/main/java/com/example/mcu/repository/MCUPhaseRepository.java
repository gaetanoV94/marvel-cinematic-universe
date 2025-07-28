package com.example.mcu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mcu.entity.MCUPhase;

@Repository
public interface MCUPhaseRepository extends JpaRepository<MCUPhase, Long> {
    
	Optional<MCUPhase> findByName(String name);

}
