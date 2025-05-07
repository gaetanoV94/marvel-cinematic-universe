package com.example.mcu.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TVShow")
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("TV SHOW")
public class TVShow extends MCUProduct{
	
	@Column(name = "number_of_seasons")
	private int numberOfSeasons;
	
	@Column(name = "number_of_episodes")
	private int numberOfEpisodes;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cast> cast = new ArrayList<>();

}
