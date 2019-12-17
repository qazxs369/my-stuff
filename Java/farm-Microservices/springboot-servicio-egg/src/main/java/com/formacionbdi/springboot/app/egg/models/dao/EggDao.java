package com.formacionbdi.springboot.app.egg.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.formacionbdi.springboot.app.commons.models.entity.Egg;

public interface EggDao extends CrudRepository<Egg, Long>{

}
