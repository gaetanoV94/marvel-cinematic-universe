package com.example.mcu.repository;

import com.example.mcu.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ActorRepository extends JpaRepository<Actor, Long>, JpaSpecificationExecutor<Actor> {
}
