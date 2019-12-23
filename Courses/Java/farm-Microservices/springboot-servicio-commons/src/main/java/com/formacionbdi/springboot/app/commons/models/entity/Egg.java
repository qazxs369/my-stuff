package com.formacionbdi.springboot.app.commons.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eggs")
public class Egg implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "egg_days")
	private int eggDays;
	
	private String species;

	
	public Egg() {

	}

	public Long getId() {
		return id;
	}

	public int getEggDays() {
		return eggDays;
	}

	public void setEggDays(int eggDays) {
		this.eggDays = eggDays;
	}
	
	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	private static final long serialVersionUID = -2386100294760967406L;
}
