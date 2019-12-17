package com.formacionbdi.springboot.app.chick.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.springboot.app.chick.models.dao.ChickDao;
import com.formacionbdi.springboot.app.commons.models.entity.Chick;

@Service
public class ChickImpl implements IChickService{

	@Autowired
	private ChickDao chickDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Chick> findAll() {
		return (List<Chick>) chickDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Chick findById(Long chickID) {
		return chickDao.findById(chickID).orElse(null);
	}

	@Override
	@Transactional
	public Chick save(Chick chick) {
		return chickDao.save(chick);
	}
	
	@Override
	@Transactional
	public Chick comprar() {
		Chick chick = new Chick();
		chick.setChickDays(0);
		return chickDao.save(chick);
	}

	@Override
	@Transactional
	public void deleteById(Long chickID) {
		chickDao.deleteById(chickID);		
	}

	@Override
	@Transactional
	public Chick updateById(Long chickID) {
		Chick chickDB = chickDao.findById(chickID).orElse(null);
		chickDB.setChickDays(chickDB.getChickDays() + 1);
		return chickDao.save(chickDB);
	}
	
}
