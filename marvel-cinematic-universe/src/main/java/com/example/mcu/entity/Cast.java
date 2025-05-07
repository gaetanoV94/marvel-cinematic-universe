package com.example.mcu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cast_MCU")
public class Cast {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cast_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "actor_id")
	private Actor actor;
	
	@Column(name = "character")
    private String character;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    private MCUProduct product;

}
