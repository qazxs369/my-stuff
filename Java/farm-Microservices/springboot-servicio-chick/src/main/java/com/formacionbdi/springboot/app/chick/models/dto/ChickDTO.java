package com.formacionbdi.springboot.app.chick.models.dto;

public class ChickDTO {

	private Long id;
	private int chickDays;

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