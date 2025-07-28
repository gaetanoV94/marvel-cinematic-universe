package com.example.mcu.repository;

import com.example.mcu.entity.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TVShowRepository extends JpaRepository<TVShow, Long>, JpaSpecificationExecutor<TVShow> {
}
