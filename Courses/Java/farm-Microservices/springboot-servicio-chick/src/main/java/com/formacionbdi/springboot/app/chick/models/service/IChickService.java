package com.formacionbdi.springboot.app.chick.models.service;

import java.util.List;

import com.formacionbdi.springboot.app.chick.models.dto.ChickDTO;
import com.formacionbdi.springboot.app.commons.models.entity.Chick;

public interface IChickService {

	public List<ChickDTO> findAllDTOS();
	
	public Chick findById(Long chickID);
	
	public Chick save(Chick chick);
	
	public void deleteById(Long chickID);
}
