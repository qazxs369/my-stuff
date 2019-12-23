package com.formacionbdi.springboot.app.farm.models.dto;

public class BirdDTO {

	private Long id;
	private int chickDays;
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

	public int getChickDays() {
		return chickDays;
	}

	public void setChickDays(int chickDays) {
		this.chickDays = chickDays;
	}

}