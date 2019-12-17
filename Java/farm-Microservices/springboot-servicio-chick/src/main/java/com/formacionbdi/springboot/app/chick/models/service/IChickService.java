package com.formacionbdi.springboot.app.chick.models.service;

import java.util.List;

import com.formacionbdi.springboot.app.commons.models.entity.Chick;

public interface IChickService {

	public List<Chick> findAll();
	public Chick findById(Long chickID);
	
	public Chick save(Chick chick);
	
	public Chick comprar();
	
	public void deleteById(Long chickID);
	
	public Chick updateById(Long chickID);
}
