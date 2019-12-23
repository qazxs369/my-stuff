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
	
	@GetMapping("/listarEggs/{species}")
	public List<EggDTO> listarChickEggs(@PathVariable String species){
		return eggService.findEggDTOSBySpecies(species);
	}
	
	@GetMapping("/verEgg/{eggID}")
	public EggDTO verEgg(@PathVariable Long eggID){
		return eggService.findEggDTOById(eggID);
	}
	
	@PostMapping("/comprarEgg/{species}")
	@ResponseStatus(HttpStatus.CREATED)
	public Egg comprarEgg(@PathVariable String species) {
		Egg egg = new Egg();
		egg.setEggDays(0);
		egg.setSpecies(species);
		return eggService.saveEgg(egg);
	}
	
	@DeleteMapping("/venderEgg/{species}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void venderEgg(@PathVariable String species) {
		eggService.deleteEggBySpecies(species);
	}
	
	@PutMapping("/updateEgg/{eggID}")
	@ResponseStatus(HttpStatus.CREATED)
	public Egg eggUpdate(@PathVariable Long eggID) {
		Egg eggDB = eggService.findEggById(eggID);
		eggDB.setEggDays(eggDB.getEggDays() + 1);
		return eggService.saveEgg(eggDB);
	}
}
