package com.formacionbdi.springboot.app.egg.models.service;

import java.util.List;

import com.formacionbdi.springboot.app.egg.models.dto.EggDTO;
import com.formacionbdi.springboot.app.commons.models.entity.Egg;

public interface IEggService {

	public List<EggDTO> findAllEggDTOS();
	
	public List<EggDTO> findChickEggDTOS();
	
	public List<EggDTO> findDuckEggDTOS();
	
	public List<EggDTO> findTurkeyEggDTOS();
	
	public Egg findById(Long eggID);
	
	public Egg save(Egg egg);
	
	public void deleteById(Long eggID);
}
