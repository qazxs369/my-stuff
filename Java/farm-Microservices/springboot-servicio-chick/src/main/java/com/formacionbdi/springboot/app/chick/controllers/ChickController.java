package com.formacionbdi.springboot.app.chick.controllers;

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

import com.formacionbdi.springboot.app.chick.models.service.IChickService;
import com.formacionbdi.springboot.app.commons.models.entity.Chick;

@RestController
public class ChickController {

	@Autowired
	private IChickService chickService;
	
	@GetMapping("/listarChicks")
	public List<Chick> listar(){
		return chickService.findAll();
	}
	
	@GetMapping("/verChick/{id}")
	public Chick detalle(@PathVariable Long chickID){
		return chickService.findById(chickID);
	}
	
	@PostMapping("/comprarChick")
	@ResponseStatus(HttpStatus.CREATED)
	public Chick comprar() {
		return chickService.comprar();
	}
	
	@DeleteMapping("/venderChick/{chickID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vender(@PathVariable Long chickID) {
		chickService.deleteById(chickID);
	}
	
	@PutMapping("/updateChick/{chickID}")
	@ResponseStatus(HttpStatus.CREATED)
	public Chick chickUpdate(@PathVariable Long chickID) {
		return chickService.updateById(chickID);
	}
	
}
