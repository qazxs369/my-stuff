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

import com.formacionbdi.springboot.app.commons.models.entity.Bird;
import com.formacionbdi.springboot.app.farm.models.dto.ChickDTO;

@FeignClient(name = "servicio-chicks")
public interface ChickClienteRest {
	
	@GetMapping("/listarChicks")
	public List<ChickDTO> listar();
	
	@GetMapping("/verChick/{chickID}")
	public Bird detalle(@PathVariable Long chickID);
	
	@PostMapping("/comprarChick")
	@ResponseStatus(HttpStatus.CREATED)
	public Bird comprar();
	
	@DeleteMapping("/venderChick/{chickID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vender(@PathVariable Long chickID);
	
	@PutMapping("/updateChick/{chickID}")
	@ResponseStatus(HttpStatus.CREATED)
	public Bird chickUpdate(@PathVariable Long chickID);

}
