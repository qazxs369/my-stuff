package com.formacionbdi.springboot.app.egg.models.service;

import java.util.List;

import com.formacionbdi.springboot.app.commons.models.entity.Egg;

public interface IEggService {

	public List<Egg> findAll();
	public Egg findById(Long id);
	
	public Egg save(Egg egg);
	
	public Egg comprar();
	
	public void deleteById(Long eggID);
	
	public Egg updateById(Long eggID);
}
