package com.example.mcu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MCUProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "product_id")
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "release_year")
	private int releaseYear;
	
	@Column(name = "director")
	private String director;
	
	@Column(name = "platform")
	private String platform;
	
	@Column(name = "budget")
    private long budget;
	
	@ManyToOne
    @JoinColumn(name = "phase_id")
    private MCUPhase phase;
	
	@Column(name = "metacritic_mark")
    private double metacriticMark;

}
