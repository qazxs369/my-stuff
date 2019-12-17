package com.formacionbdi.springboot.app.farm.models.dto;

public class EggDTO {

	private Long id;
	private int eggDays;
	private String species;

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getEggDays() {
		return eggDays;
	}

	public void setEggDays(int eggDays) {
		this.eggDays = eggDays;
	}

}