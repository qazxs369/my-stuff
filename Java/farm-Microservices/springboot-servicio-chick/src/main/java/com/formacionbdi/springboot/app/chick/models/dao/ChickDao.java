package com.formacionbdi.springboot.app.chick.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.formacionbdi.springboot.app.commons.models.entity.Chick;

public interface ChickDao extends CrudRepository<Chick, Long>{

}
