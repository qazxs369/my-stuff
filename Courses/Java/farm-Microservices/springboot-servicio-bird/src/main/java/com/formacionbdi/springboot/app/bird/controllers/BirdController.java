package com.formacionbdi.springboot.app.bird.controllers;

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

import com.formacionbdi.springboot.app.bird.models.dto.BirdDTO;
import com.formacionbdi.springboot.app.bird.models.service.IBirdService;
import com.formacionbdi.springboot.app.commons.models.entity.Bird;

@RestController
public class BirdController {

	@Autowired
	private IBirdService chickService;
	
	@GetMapping("/listarChicks")
	public List<BirdDTO> listar(){
		return chickService.findAllDTOS();
	}
	
	@GetMapping("/verChick/{chickID}")
	public Bird detalle(@PathVariable Long chickID){
		return chickService.findById(chickID);
	}
	
	@PostMapping("/comprarChick")
	@ResponseStatus(HttpStatus.CREATED)
	public Bird comprar() {
		Bird chick = new Bird();
		chick.setChickDays(0);
		return chickService.save(chick);
	}
	
	@DeleteMapping("/venderChick/{chickID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vender(@PathVariable Long chickID) {
		chickService.deleteById(chickID);
	}
	
	@PutMapping("/updateChick/{chickID}")
	@ResponseStatus(HttpStatus.CREATED)
	public Bird chickUpdate(@PathVariable Long chickID) {
		Bird chickDB = chickService.findById(chickID);
		chickDB.setChickDays(chickDB.getChickDays() + 1);
		return chickService.save(chickDB);
	}
	
}
