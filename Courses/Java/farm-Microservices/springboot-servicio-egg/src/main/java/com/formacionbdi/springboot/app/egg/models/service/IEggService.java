package com.formacionbdi.springboot.app.egg.models.service;

import java.util.List;

import com.formacionbdi.springboot.app.egg.models.dto.EggDTO;
import com.formacionbdi.springboot.app.commons.models.entity.Egg;

public interface IEggService {

	public List<EggDTO> findAllEggDTOS();
	
	public List<EggDTO> findEggDTOSBySpecies(String species);
	
	public EggDTO findEggDTOById(Long eggID);
	
	public Egg findEggById(Long eggID);
	
	public Egg saveEgg(Egg egg);
	
	public void deleteEggBySpecies(String species);
}
