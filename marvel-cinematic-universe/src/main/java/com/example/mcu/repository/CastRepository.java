package com.example.mcu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mcu.entity.Cast;

@Repository
public interface CastRepository extends JpaRepository<Cast, Long> {
	
	@Query("SELECT c from Cast_MCU c where c.character = :character")
    List<Cast> findByCharacter(@Param("character") String character);
	
	@Query("SELECT c FROM Cast c WHERE c.actor.name = :nome AND c.actor.surname = :surname")
    List<Cast> findCastByActorFullName(@Param("name") String name, @Param("surname") String surname);
	
	@Query("SELECT c FROM Cast c WHERE LOWER(c.actor.name) LIKE LOWER(CONCAT('%', :nameOrSurname, '%')) " +
	           "OR LOWER(c.actor.surname) LIKE LOWER(CONCAT('%', :nameOrSurname, '%'))")
	    List<Cast> findCastByActorNameOrSurname(@Param("nameOrSurname") String nameOrSurname);
	
	
	@Query("SELECT c FROM Cast_MCU c "
			+ "WHERE c.product IN "
			+ "(SELECT m FROM Movie m WHERE LOWER(m.title) = LOWER(:title)) "
			+ "OR c.product IN "
			+ "(SELECT t FROM TVShow t WHERE LOWER(t.title) = LOWER(:title))")
	    List<Cast> findCastByTitle(@Param("title") String title);
	
	
	@Query("SELECT c FROM Cast_MCU c"
	       + "WHERE c.product IN"
	       +     "(SELECT m FROM Movie m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%')))" 
	       + "OR c.product IN"
	       +     "(SELECT t FROM TVShow s WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%')))")
	    List<Cast> findCastByTitleLike(@Param("title") String title);
}
