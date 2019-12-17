package com.formacionbdi.springboot.app.farm.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.formacionbdi.springboot.app.commons.models.entity.Egg;

@FeignClient(name = "servicio-eggs", url="localhost:8001")
public interface EggClienteRest {
	
	@GetMapping("/listarEggs")
	public List<Egg> listar();
	
	@GetMapping("/verEgg/{id}")
	public Egg detalle(@PathVariable Long id);
	
	@PostMapping("/comprarEgg")
	@ResponseStatus(HttpStatus.CREATED)
	public Egg comprar();

	@DeleteMapping("/venderEgg/{eggID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vender(@PathVariable Long eggID);
	
	@PutMapping("/updateEgg/{eggID}")
	@ResponseStatus(HttpStatus.CREATED)
	public Egg eggUpdate(@PathVariable Long eggID);
}
