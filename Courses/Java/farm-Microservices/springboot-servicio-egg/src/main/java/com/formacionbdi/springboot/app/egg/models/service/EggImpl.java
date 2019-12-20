package com.formacionbdi.springboot.app.egg.models.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.springboot.app.egg.models.dao.EggDao;
import com.formacionbdi.springboot.app.egg.models.dto.EggDTO;
import com.formacionbdi.springboot.app.commons.models.entity.Egg;

@Service
public class EggImpl implements IEggService{

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Autowired 
	private EggDao eggDao;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<EggDTO> findAllEggDTOS() {
		ModelMapper modelMapper = new ModelMapper();
		List<EggDTO> eggDTOS = new ArrayList<>();
		List<Egg> eggs = (List<Egg>) eggDao.findAll();
		for (int i = 0; i < eggs.size(); i++) {
			EggDTO eggDTO = modelMapper.map(eggs.get(i), EggDTO.class);
			eggDTOS.add(eggDTO);
		}
		return eggDTOS;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<EggDTO> findChickEggDTOS() {
		ModelMapper modelMapper = new ModelMapper();
		List<EggDTO> eggDTOS = new ArrayList<>();
		List<Egg> eggs = (List<Egg>) eggDao.findAll();
		for (int i = 0; i < eggs.size(); i++) {
			if(eggs.get(i).getSpecies() == "Chick") {
				EggDTO eggDTO = modelMapper.map(eggs.get(i), EggDTO.class);
				eggDTOS.add(eggDTO);
			}
		}
		return eggDTOS;
	}

	@Override
	@Transactional(readOnly = true)
	public List<EggDTO> findDuckEggDTOS() {
		ModelMapper modelMapper = new ModelMapper();
		List<EggDTO> eggDTOS = new ArrayList<>();
		List<Egg> eggs = (List<Egg>) eggDao.findAll();
		for (int i = 0; i < eggs.size(); i++) {
			if(eggs.get(i).getSpecies() == "Duck") {
				EggDTO eggDTO = modelMapper.map(eggs.get(i), EggDTO.class);
				eggDTOS.add(eggDTO);
			}
		}
		return eggDTOS;
	}

	@Override
	@Transactional(readOnly = true)
	public List<EggDTO> findTurkeyEggDTOS() {
		ModelMapper modelMapper = new ModelMapper();
		List<EggDTO> eggDTOS = new ArrayList<>();
		List<Egg> eggs = (List<Egg>) eggDao.findAll();
		for (int i = 0; i < eggs.size(); i++) {
			if(eggs.get(i).getSpecies() == "Turkey") {
				EggDTO eggDTO = modelMapper.map(eggs.get(i), EggDTO.class);
				eggDTOS.add(eggDTO);
			}
		}
		return eggDTOS;
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
	public void deleteById(Long eggID) {
		eggDao.deleteById(eggID);		
	}

}
