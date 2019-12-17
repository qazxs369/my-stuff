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

import com.formacionbdi.springboot.app.commons.models.entity.Chick;

@FeignClient(name = "servicio-chicks", url="localhost:8003")
public interface ChickClienteRest {
	
	@GetMapping("/listarChicks")
	public List<Chick> listar();
	
	@GetMapping("/verChick/{id}")
	public Chick detalle(@PathVariable Long id);
	
	@PostMapping("/comprarChick")
	@ResponseStatus(HttpStatus.CREATED)
	public Chick comprar();
	
	@DeleteMapping("/venderChick/{chickID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vender(@PathVariable Long chickID);
	
	@PutMapping("/updateChick/{chickID}")
	@ResponseStatus(HttpStatus.CREATED)
	public Chick chickUpdate(@PathVariable Long chickID);

}
