package com.example.mcu.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Movie")
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("MOVIE")
public class Movie extends MCUProduct{

	@Column(name = "running_time")
	private int runningTime;
	
	@Column(name = "gross")
    private long gross;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cast> cast = new ArrayList<>();
	

}
