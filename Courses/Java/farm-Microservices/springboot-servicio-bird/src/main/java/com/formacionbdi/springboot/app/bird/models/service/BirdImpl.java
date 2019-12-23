package com.formacionbdi.springboot.app.bird.models.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.springboot.app.bird.models.dao.BirdDao;
import com.formacionbdi.springboot.app.bird.models.dto.BirdDTO;
import com.formacionbdi.springboot.app.commons.models.entity.Bird;

@Service
public class BirdImpl implements IBirdService{

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Autowired
	private BirdDao chickDao;

	@Autowired
    private ModelMapper modelMapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<BirdDTO> findAllDTOS() {
		ModelMapper modelMapper = new ModelMapper();
		List<BirdDTO> chickDTOS = new ArrayList<>();
		List<Bird> chicks = (List<Bird>) chickDao.findAll();
		for (int i = 0; i < chicks.size(); i++) {
			BirdDTO chickDTO = modelMapper.map(chicks.get(i), BirdDTO.class);
			chickDTOS.add(chickDTO);
		}
		return chickDTOS;
	}

	@Override
	@Transactional(readOnly = true)
	public Bird findById(Long chickID) {
		return chickDao.findById(chickID).orElse(null);
	}

	@Override
	@Transactional
	public Bird save(Bird chick) {
		return chickDao.save(chick);
	}

	@Override
	@Transactional
	public void deleteById(Long chickID) {
		chickDao.deleteById(chickID);		
	}

	
}
