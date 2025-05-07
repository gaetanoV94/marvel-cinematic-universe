package com.example.mcu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mcu.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
    List<Movie> findByTitle(@Param("title") String title);

    @Query("SELECT m FROM Movie m WHERE m.director LIKE %:director%")
    List<Movie> findByDirector(@Param("director") String director);

    @Query("SELECT m FROM Movie m WHERE m.gross > :gross")
    List<Movie> findByGrossGreaterThan(@Param("gross") double gross);

    @Query("SELECT m FROM Movie m WHERE m.running_time BETWEEN :minDurata AND :maxDurata")
    List<Movie> findByRunningTimeBetween(@Param("minDurata") int minDurata, @Param("maxDurata") int maxDurata);

    @Query("SELECT m FROM Movie m WHERE m.budget < :budget")
    List<Movie> findByBudgetLessThan(@Param("budget") double budget);

    @Query("SELECT m FROM Movie m WHERE m.metacritic_mark > :metacriticMark")
    List<Movie> findByMetacriticMarkGreaterThan(@Param("metacritic_mark") double metacriticMark);

    @Query("SELECT m FROM Movie m JOIN m.phase fm WHERE fm.name = :phaseName")
    List<Movie> findByPhaseName(@Param("phaseName") String phaseName);
}

