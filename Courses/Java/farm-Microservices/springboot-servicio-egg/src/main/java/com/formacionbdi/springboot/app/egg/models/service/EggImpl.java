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
	public List<EggDTO> findEggDTOSBySpecies(String species) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<EggDTO> eggDTOS = new ArrayList<>();
		List<Egg> eggs = (List<Egg>) eggDao.findAll();
		
		for (int i = 0; i < eggs.size(); i++) {
			if (eggs.get(i).getSpecies() == species) {
				
				EggDTO eggDTO = modelMapper.map(eggs.get(i), EggDTO.class);
				
				eggDTOS.add(eggDTO);
			}
		}
		return eggDTOS;
	}
	
	@Override
	@Transactional(readOnly = true)
	public EggDTO findEggDTOById(Long eggID) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		Egg egg = eggDao.findById(eggID).orElse(null);
		return modelMapper.map(egg, EggDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Egg findEggById(Long eggID) {
		return eggDao.findById(eggID).orElse(null);
	}

	@Override
	@Transactional
	public Egg saveEgg(Egg egg) {
		return eggDao.save(egg);
	}

	@Override
	@Transactional
	public void deleteEggBySpecies(String species) {
		
		List<EggDTO> eggDTOS = findEggDTOSBySpecies(species);
		
		if (!eggDTOS.isEmpty()) {
			Long eggID = eggDTOS.get(0).getId();
			eggDao.deleteById(eggID);
		}		
	}

}
