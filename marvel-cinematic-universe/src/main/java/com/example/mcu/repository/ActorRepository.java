package com.example.mcu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mcu.entity.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    
    @Query("SELECT DISTINCT c.actor FROM Cast c WHERE LOWER(c.character) LIKE LOWER(CONCAT('%', :character, '%'))")
    List<Actor> findActorsByCharacter(@Param("character") String character);

    @Query("SELECT c.actor, COUNT(c) FROM Cast_MCU c GROUP BY c.actor ORDER BY COUNT(c) DESC")
    List<Object[]> findMostFeaturedActors();
    
    @Query("""
    	    SELECT DISTINCT c.actor FROM Cast c 
    	    WHERE c.product IN (
    	        SELECT f FROM Movie f WHERE LOWER(f.titolo) LIKE LOWER(CONCAT('%', :titolo, '%'))
    	    ) OR c.product IN (
    	        SELECT s FROM TVShow s WHERE LOWER(s.titolo) LIKE LOWER(CONCAT('%', :titolo, '%'))
    	    )
    	""")
	List<Actor> findAttoriByTitoloProdotto(@Param("titolo") String titolo);
    
    @Query("SELECT a FROM Actor a WHERE LOWER(a.nome) = LOWER(:nome)")
    List<Actor> findByNameIgnoreCase(@Param("nome") String nome);

    @Query("SELECT a FROM Actor a WHERE LOWER(a.cognome) = LOWER(:cognome)")
    List<Actor> findBySurnameIgnoreCase(@Param("cognome") String cognome);

    @Query("SELECT a FROM Actor a WHERE LOWER(a.nome) = LOWER(:nome) AND LOWER(a.cognome) = LOWER(:cognome)")
    List<Actor> findByNameAndSurnameIgnoreCase(@Param("nome") String nome, @Param("cognome") String cognome);



}

