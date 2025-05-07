package com.example.mcu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mcu.entity.TVShow;

@Repository
public interface TVShowRepository extends JpaRepository<TVShow, Long> {

    @Query("SELECT t FROM TVShow t WHERE s.title LIKE %:title%")
    List<TVShow> findByTitle(@Param("title") String title);
    
    @Query("SELECT t FROM TVShow t WHERE t.director LIKE %:director%")
    List<TVShow> findByDirector(@Param("director") String director);

    @Query("SELECT t FROM TVShow t WHERE s.platform LIKE %:platform%")
    List<TVShow> findByPlatform(@Param("platform") String platform);

    @Query("SELECT t FROM TVShow t WHERE t.budget < :budget")
    List<TVShow> findByBudgetLessThan(@Param("budget") double budget);
    
    @Query("SELECT t FROM TVShow t WHERE t.number_of_episodes > :numberOfEpisodes")
    List<TVShow> findByNumberOfEpisodesGreaterThan(@Param("numberOfEpisodes") int numberOfEpisodes);

    @Query("SELECT m FROM Movie m WHERE m.metacritic_mark > :metacriticMark")
    List<TVShow> findByMetacriticMarkGreaterThan(@Param("metacritic_mark") double metacriticMark);
    
    @Query("SELECT t FROM TVShow t JOIN s.phase fm WHERE fm.name = :phaseName")
    List<TVShow> findByPhaseName(@Param("phaseName") String phaseName);
}

