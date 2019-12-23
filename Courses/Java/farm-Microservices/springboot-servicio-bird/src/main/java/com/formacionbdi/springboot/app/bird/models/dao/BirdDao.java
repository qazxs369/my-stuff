package com.formacionbdi.springboot.app.bird.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.formacionbdi.springboot.app.commons.models.entity.Bird;

public interface BirdDao extends CrudRepository<Bird, Long>{

}
