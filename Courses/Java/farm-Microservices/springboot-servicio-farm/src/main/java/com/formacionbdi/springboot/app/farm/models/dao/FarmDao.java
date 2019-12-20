package com.formacionbdi.springboot.app.farm.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.formacionbdi.springboot.app.commons.models.entity.Farm;

public interface FarmDao extends CrudRepository<Farm, Long> {

}
