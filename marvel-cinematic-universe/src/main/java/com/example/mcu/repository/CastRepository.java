package com.example.mcu.repository;

import com.example.mcu.entity.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CastRepository extends JpaRepository<Cast, Long>, JpaSpecificationExecutor<Cast> {
}
