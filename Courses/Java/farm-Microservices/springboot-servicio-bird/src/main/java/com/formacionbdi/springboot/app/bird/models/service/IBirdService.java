package com.formacionbdi.springboot.app.bird.models.service;

import java.util.List;

import com.formacionbdi.springboot.app.bird.models.dto.BirdDTO;
import com.formacionbdi.springboot.app.commons.models.entity.Bird;

public interface IBirdService {

	public List<BirdDTO> findAllBirdDTOS();
	
	public Bird findBirdById(Long birdID);
	
	public BirdDTO findBirdDTOById(Long birdID);
	
	public Bird saveBird(Bird bird);
	
	public void deleteById(Long birdID);
}
