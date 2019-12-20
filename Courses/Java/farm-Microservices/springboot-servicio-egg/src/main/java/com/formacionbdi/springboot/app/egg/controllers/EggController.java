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
import com.formacionbdi.springboot.app.egg.models.dto.EggDTO;
import com.formacionbdi.springboot.app.egg.models.service.IEggService;

@RestController
public class EggController {

	@Autowired
	private IEggService eggService;
	
	@GetMapping("/listarEggs")
	public List<EggDTO> listarEggs(){
		return eggService.findAllEggDTOS();
	}
	
	@GetMapping("/listarChickEggs")
	public List<EggDTO> listarChickEggs(){
		return eggService.findChickEggDTOS();
	}
	
	@GetMapping("/listarDuckEggs")
	public List<EggDTO> listarDuckEggs(){
		return eggService.findDuckEggDTOS();
	}
	
	@GetMapping("/listarTurkeyEggs")
	public List<EggDTO> listarTurkeyEggs(){
		return eggService.findTurkeyEggDTOS();
	}
	
	@GetMapping("/verEgg/{eggID}")
	public Egg detalle(@PathVariable Long eggID){
		return eggService.findById(eggID);
	}
	
	@PostMapping("/comprarEgg")
	@ResponseStatus(HttpStatus.CREATED)
	public Egg comprar() {
		Egg egg = new Egg();
		egg.setEggDays(0);
		return eggService.save(egg);
	}
	
	@DeleteMapping("/venderEgg/{eggID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vender(@PathVariable Long eggID) {
		eggService.deleteById(eggID);
	}
	
	@PutMapping("/updateEgg/{eggID}")
	@ResponseStatus(HttpStatus.CREATED)
	public Egg eggUpdate(@PathVariable Long eggID) {
		Egg eggDB = eggService.findById(eggID);
		eggDB.setEggDays(eggDB.getEggDays() + 1);
		return eggService.save(eggDB);
	}
}
