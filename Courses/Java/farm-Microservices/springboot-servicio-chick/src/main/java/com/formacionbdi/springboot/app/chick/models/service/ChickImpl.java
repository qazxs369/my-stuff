package com.formacionbdi.springboot.app.chick.models.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.springboot.app.chick.models.dao.ChickDao;
import com.formacionbdi.springboot.app.chick.models.dto.ChickDTO;
import com.formacionbdi.springboot.app.commons.models.entity.Chick;

@Service
public class ChickImpl implements IChickService{

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Autowired
	private ChickDao chickDao;

	@Autowired
    private ModelMapper modelMapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<ChickDTO> findAllDTOS() {
		ModelMapper modelMapper = new ModelMapper();
		List<ChickDTO> chickDTOS = new ArrayList<>();
		List<Chick> chicks = (List<Chick>) chickDao.findAll();
		for (int i = 0; i < chicks.size(); i++) {
			ChickDTO chickDTO = modelMapper.map(chicks.get(i), ChickDTO.class);
			chickDTOS.add(chickDTO);
		}
		return chickDTOS;
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
	public void deleteById(Long chickID) {
		chickDao.deleteById(chickID);		
	}

	
}
