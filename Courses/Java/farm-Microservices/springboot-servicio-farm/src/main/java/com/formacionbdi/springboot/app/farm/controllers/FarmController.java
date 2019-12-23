package com.formacionbdi.springboot.app.farm.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.commons.models.entity.Bird;
import com.formacionbdi.springboot.app.commons.models.entity.Egg;
import com.formacionbdi.springboot.app.commons.models.entity.Farm;
import com.formacionbdi.springboot.app.farm.models.dto.ChickDTO;
import com.formacionbdi.springboot.app.farm.models.dto.EggDTO;
import com.formacionbdi.springboot.app.farm.models.service.IFarmService;

@RestController
@EnableScheduling
public class FarmController {

	@Value("${farm.money}")
	double defaultMoney;
	
	@Autowired
	private IFarmService farmService;

	private List<EggDTO> eggDTO() {
		return farmService.findAllFarmEggDTOS();
	}

	private List<ChickDTO> chickDTO() {
		return farmService.findAllFarmChickDTOS();
	}

	private int comprasEggDTO(Long farmID) {
		return farmService.findFarmById(farmID).getComprasEgg();
	}

	private int ventasEggDTO(Long farmID) {
		return farmService.findFarmById(farmID).getVentasEgg();
	}

	private int comprasChickDTO(Long farmID) {
		return farmService.findFarmById(farmID).getComprasChick();
	}

	private int ventasChickDTO(Long farmID) {
		return farmService.findFarmById(farmID).getVentasChick();
	}

	private Map<String, Object> reportDTO(Long farmID) {
		Map<String, Object> dto = new LinkedHashMap<String, Object>();
		dto.put("Money", farmService.getMoney(farmID));
		dto.put("N° of Eggs", eggDTO().size());
		dto.put("N° of Chicks", chickDTO().size());
		dto.put("Compras de eggs", comprasEggDTO(farmID));
		dto.put("Compras de chicks", comprasChickDTO(farmID));
		dto.put("Ventas de eggs", ventasEggDTO(farmID));
		dto.put("Ventas de chicks", ventasChickDTO(farmID));
		dto.put("Egg hatches:", farmService.getEggHatches(farmID));
		dto.put("Layed eggs:", farmService.getLayedEggs(farmID));
		dto.put("Trips to heaven:", farmService.getCasualties(farmID));
		dto.put("List of Eggs", eggDTO());
		dto.put("List of Chicks", chickDTO());
		return dto;
	}
	
	/*
	 * End points!
	 * 
	 */
	
	@PostMapping("/abrirGranja")
	public ResponseEntity<Object> iniciarGranjaDefault() {
		double starterMoney = defaultMoney;
		if (!farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>("Already in a farm", HttpStatus.FORBIDDEN);
		}
		Farm farm = new Farm(starterMoney, 0, 0, 0, 0, 0, 0, 0);
		farmService.saveFarm(farm);
		return new ResponseEntity<>("[Starter Money: " + starterMoney + "] " + 
				"Welcome! Got some rare things on sale, stranger", HttpStatus.CREATED);
	}
	
	@PostMapping("/abrirGranja/{money}")
	public ResponseEntity<Object> iniciar(@PathVariable double money) {
		double starterMoney = money;
		if (!farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>("Already in a farm", HttpStatus.FORBIDDEN);
		}
		Farm farm = new Farm(starterMoney, 0, 0, 0, 0, 0, 0, 0);
		farmService.saveFarm(farm);
		return new ResponseEntity<>("[Starter Money: " + starterMoney + "] " + 
				"Welcome! Got some rare things on sale, stranger", HttpStatus.CREATED);
	}

	@PostMapping("/comprarEgg/cantidad/{x}")
	public ResponseEntity<Object> comprarEgg(@PathVariable int x) {
		if (farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>("No farm", HttpStatus.FORBIDDEN);
		}
		if (farmService.findFarmById((long) 1).getMoney() < 10 * x) {
			return new ResponseEntity<>("Not enough cash, stranger", HttpStatus.FORBIDDEN);
		}
		for (int i = 0; i < x; i++) {
			farmService.spendMoneyEgg((long) 1);
			farmService.saveFarmEgg();
		}
		return new ResponseEntity<>("[Bought egg(s)] Hehehehe, thank you", HttpStatus.CREATED);
	}

	@DeleteMapping("/venderEgg/cantidad/{x}")
	public ResponseEntity<Object> venderEgg(@PathVariable int x) {
		if (farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>("No farm", HttpStatus.FORBIDDEN);
		}
		if (farmService.findAllFarmEggDTOS().size() < x) {
			return new ResponseEntity<>("Not enough eggs, stranger", HttpStatus.FORBIDDEN);
		}
		for (int i = 0; i < x; i++) {
			farmService.earnMoneyEgg((long) 1);
			long eggID = farmService.findAllFarmEggDTOS().get(0).getId();
			farmService.deleteFarmEggById(eggID);
		}
		return new ResponseEntity<>("[Sold egg(s)] Hehehehe, thank you",
				HttpStatus.OK);
	}

	@PostMapping("/comprarChick/cantidad/{x}")
	public ResponseEntity<Object> comprarChick(@PathVariable int x) {
		if (farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>("No farm", HttpStatus.FORBIDDEN);
		}
		if (farmService.findFarmById((long) 1).getMoney() < 100 * x) {
			return new ResponseEntity<>("Not enough cash, stranger", HttpStatus.FORBIDDEN);
		}
		for (int i = 0; i < x; i++) {
			farmService.spendMoneyChick((long) 1);
			farmService.saveFarmChick();
		}
		return new ResponseEntity<>("[Bought chick(s)] Hehehehe, thank you", HttpStatus.CREATED);
	}

	@DeleteMapping("/venderChick/cantidad/{x}")
	public ResponseEntity<Object> venderChick(@PathVariable int x) {
		if (farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>("No farm", HttpStatus.FORBIDDEN);
		}
		if (farmService.findAllFarmChickDTOS().size() < x) {
			return new ResponseEntity<>("Not enough chicks, stranger", HttpStatus.FORBIDDEN);
		}
		for (int i = 0; i < x; i++) {
			farmService.earnMoneyChick((long) 1);
			long chickID = farmService.findAllFarmChickDTOS().get(0).getId();
			farmService.deleteFarmChickById(chickID);
		}
		return new ResponseEntity<>("[Sold chick(s)] Hehehehe, thank you", HttpStatus.OK);
	}

	@GetMapping("/reporte")
	public ResponseEntity<Object> reporte() {
		if (farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>("No farm", HttpStatus.FORBIDDEN);
		}
		Map<String, Object> reportDTO = reportDTO((long) 1);
		return new ResponseEntity<>(reportDTO, HttpStatus.OK);
	}
	
	//@Scheduled(fixedRate = 10000)
	@PutMapping("/tomorrow")
	public ResponseEntity<Object> tomorrow(){
		if (farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>("No farm", HttpStatus.FORBIDDEN);
		}
		List<EggDTO> farmEggDTOS = farmService.findAllFarmEggDTOS();
		
		for (int i = 0; i < farmEggDTOS.size(); i++) {
			Egg farmEgg = farmService.findEggById(farmEggDTOS.get(i).getId());
			if(farmEgg.getEggDays() < 3) {
				farmService.updateEggById(farmEggDTOS.get(i).getId());
			}
			else {
				farmService.deleteFarmEggById(farmEgg.getId());
				farmService.saveFarmChick();
				farmService.huboEggHatch((long) 1);
			}
		}
		
		List<ChickDTO> farmChickDTOS = farmService.findAllFarmChickDTOS();
		
		for (int i = 0; i < farmChickDTOS.size(); i++) {
			Bird farmChick = farmService.findChickById(farmChickDTOS.get(i).getId());
			if(farmChick.getChickDays() < 10) {
				farmService.updateChickById(farmChickDTOS.get(i).getId());
				Integer chickDays = farmChick.getChickDays();
				if(chickDays.equals(3) || chickDays.equals(6)) {
					farmService.saveFarmEgg();
					farmService.huboLayedEgg((long) 1);
				}
			}
			else {
				farmService.deleteFarmChickById(farmChick.getId());
				farmService.huboCasualty((long) 1);
			}
		}
		return new ResponseEntity<>("The next day...", HttpStatus.OK);
	}

}
