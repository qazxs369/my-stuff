package com.formacionbdi.springboot.app.commons.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "birds")
public class Bird implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "bird_days")
	private int birdDays;

	private String species;
	
	
	public void setId(Long id) {
		this.id = id;
	}

	public Bird() {
		
	}

	public Long getId() {
		return id;
	}

	public int getBirdDays() {
		return birdDays;
	}

	public void setChickDays(int birdDays) {
		this.birdDays = birdDays;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	private static final long serialVersionUID = -5870030148959389696L;
}
