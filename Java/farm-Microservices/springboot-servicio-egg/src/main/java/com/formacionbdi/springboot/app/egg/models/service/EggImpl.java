package com.formacionbdi.springboot.app.egg.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.springboot.app.egg.models.dao.EggDao;
import com.formacionbdi.springboot.app.commons.models.entity.Egg;

@Service
public class EggImpl implements IEggService{

	@Autowired 
	private EggDao eggDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Egg> findAll() {
		return (List<Egg>) eggDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Egg findById(Long eggID) {
		return eggDao.findById(eggID).orElse(null);
	}

	@Override
	@Transactional
	public Egg save(Egg egg) {
		return eggDao.save(egg);
	}
	
	@Override
	@Transactional
	public Egg comprar() {
		Egg egg = new Egg();
		egg.setEggDays(0);
		return eggDao.save(egg);
	}

	@Override
	@Transactional
	public void deleteById(Long eggID) {
		eggDao.deleteById(eggID);		
	}

	@Override
	@Transactional
	public Egg updateById(Long eggID) {
		Egg eggDB = eggDao.findById(eggID).orElse(null);
		eggDB.setEggDays(eggDB.getEggDays() + 1);
		return eggDao.save(eggDB);
	}

}
