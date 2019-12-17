package com.formacionbdi.springboot.app.egg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.commons.models.entity.Egg;
import com.formacionbdi.springboot.app.egg.models.service.IEggService;

@RestController
public class EggController {

	@Autowired
	private IEggService eggService;
	
	@GetMapping("/listarEggs")
	public List<Egg> listar(){
		return eggService.findAll();
	}
	
	@GetMapping("/verEgg/{eggID}")
	public Egg detalle(@PathVariable Long eggID){
		return eggService.findById(eggID);
	}
	
	@PostMapping("/comprarEgg")
	@ResponseStatus(HttpStatus.CREATED)
	public Egg comprar() {
		return eggService.comprar();
	}
	
	@DeleteMapping("/venderEgg/{eggID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vender(@PathVariable Long eggID) {
		eggService.deleteById(eggID);
	}
	
	@PutMapping("/updateEgg/{eggID}")
	@ResponseStatus(HttpStatus.CREATED)
	public Egg eggUpdate(@PathVariable Long eggID) {
		return eggService.updateById(eggID);
	}
}
