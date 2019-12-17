package com.formacionbdi.springboot.app.farm.controllers;

import java.util.HashMap;
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

import com.formacionbdi.springboot.app.commons.models.entity.Chick;
import com.formacionbdi.springboot.app.commons.models.entity.Egg;
import com.formacionbdi.springboot.app.commons.models.entity.Farm;
import com.formacionbdi.springboot.app.farm.models.service.FarmService;

@RestController
@EnableScheduling
public class FarmController {

	@Value("${farm.money}")
	double defaultMoney;
	
	@Autowired
	private FarmService farmService;

	private Map<String, Object> makeMap(String key, Object value) {
		Map<String, Object> map = new HashMap<>();
		map.put(key, value);
		return map;
	}

	private List<Egg> eggDTO() {
		return farmService.findAllFarmEggs();
	}

	private List<Chick> chickDTO() {
		return farmService.findAllFarmChicks();
	}

	private int comprasEggDTO(Long farmID) {
		return farmService.findById(farmID).getComprasEgg();
	}

	private int ventasEggDTO(Long farmID) {
		return farmService.findById(farmID).getVentasEgg();
	}

	private int comprasChickDTO(Long farmID) {
		return farmService.findById(farmID).getComprasChick();
	}

	private int ventasChickDTO(Long farmID) {
		return farmService.findById(farmID).getVentasChick();
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
		dto.put("Eggs", eggDTO());
		dto.put("Chicks", chickDTO());
		return dto;
	}
	
	private Map<String, Object> tomorrowDTO(Long farmID) {
		Map<String, Object> dto = new LinkedHashMap<String, Object>();
		dto.put("The next day...", "Welcome stranger");
		return dto;
	}
	
	
	

	
	@PostMapping("/abrirGranja")
	public ResponseEntity<Map<String, Object>> iniciarGranjaDefault() {
		double starterMoney = defaultMoney;
		if (!farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>(makeMap("Error", "Already in a farm"), 
					HttpStatus.FORBIDDEN);
		}
		Farm farm = new Farm(starterMoney, 0, 0, 0, 0, 0, 0, 0);
		farmService.saveFarm(farm);
		return new ResponseEntity<>(makeMap("Farm opened, Welcome stranger", "Money: " + starterMoney),
				HttpStatus.CREATED);
	}
	
	@PostMapping("/abrirGranja/{money}")
	public ResponseEntity<Map<String, Object>> iniciar(@PathVariable double money) {
		double starterMoney = money;
		if (!farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>(makeMap("Error", "Already in a farm"), 
					HttpStatus.FORBIDDEN);
		}
		Farm farm = new Farm(starterMoney, 0, 0, 0, 0, 0, 0, 0);
		farmService.saveFarm(farm);
		return new ResponseEntity<>(makeMap("Farm opened, Welcome stranger", "Money: " + starterMoney),
				HttpStatus.CREATED);
	}

	@PostMapping("/comprarEgg/cantidad/{x}")
	public ResponseEntity<Map<String, Object>> comprarEgg(@PathVariable int x) {
		if (farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>(makeMap("Error", "No farm"), HttpStatus.FORBIDDEN);
		}
		if (farmService.findById((long) 1).getMoney() < 10 * x) {
			return new ResponseEntity<>(makeMap("Error", "Not enough cash, stranger"),
					HttpStatus.FORBIDDEN);
		}
		for (int i = 0; i < x; i++) {
			farmService.spendMoneyEgg((long) 1);
			farmService.saveFarmEgg();
		}
		return new ResponseEntity<>(makeMap("[Bought egg(s)]", "Hehehehe, thank you"),
				HttpStatus.CREATED);
	}

	@DeleteMapping("/venderEgg/cantidad/{x}")
	public ResponseEntity<Map<String, Object>> venderEgg(@PathVariable int x) {
		if (farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>(makeMap("Error", "No farm"), HttpStatus.FORBIDDEN);
		}
		if (farmService.findAllFarmEggs().size() < x) {
			return new ResponseEntity<>(makeMap("Error", "Not enough eggs, stranger"),
					HttpStatus.FORBIDDEN);
		}
		for (int i = 0; i < x; i++) {
			farmService.earnMoneyEgg((long) 1);
			long eggID = farmService.findAllFarmEggs().get(0).getId();
			farmService.deleteFarmEggById(eggID);
		}
		return new ResponseEntity<>(makeMap("[Sold egg(s)]", "Hehehehe, thank you"),
				HttpStatus.OK);
	}

	@PostMapping("/comprarChick/cantidad/{x}")
	public ResponseEntity<Map<String, Object>> comprarChick(@PathVariable int x) {
		if (farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>(makeMap("Error", "No farm"), HttpStatus.FORBIDDEN);
		}
		if (farmService.findById((long) 1).getMoney() < 100 * x) {
			return new ResponseEntity<>(makeMap("Error", "Not enough cash, stranger"),
					HttpStatus.FORBIDDEN);
		}
		for (int i = 0; i < x; i++) {
			farmService.spendMoneyChick((long) 1);
			farmService.saveFarmChick();
		}
		return new ResponseEntity<>(makeMap("[Bought chick(s)]", "Hehehehe, thank you"),
				HttpStatus.CREATED);
	}

	@DeleteMapping("/venderChick/cantidad/{x}")
	public ResponseEntity<Map<String, Object>> venderChick(@PathVariable int x) {
		if (farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>(makeMap("Error", "No farm"), HttpStatus.FORBIDDEN);
		}
		if (farmService.findAllFarmChicks().size() < x) {
			return new ResponseEntity<>(makeMap("Error", "Not enough chicks, stranger"),
					HttpStatus.FORBIDDEN);
		}
		for (int i = 0; i < x; i++) {
			farmService.earnMoneyChick((long) 1);
			long chickID = farmService.findAllFarmChicks().get(0).getId();
			farmService.deleteFarmChickById(chickID);
		}
		return new ResponseEntity<>(makeMap("[Sold chick(s)]", "Hehehehe, thank you"),
				HttpStatus.OK);
	}

	@GetMapping("/reporte")
	public ResponseEntity<Map<String, Object>> reporte() {
		if (farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>(makeMap("Error", "No farm"), 
					HttpStatus.FORBIDDEN);
		}
		Map<String, Object> reportDTO = reportDTO((long) 1);
		return new ResponseEntity<>(reportDTO, HttpStatus.OK);
	}
	
	@Scheduled(fixedRate = 10000)
	@PutMapping("/tomorrow")
	public ResponseEntity<Map<String, Object>> tomorrow(){
		if (farmService.findAllFarms().isEmpty()) {
			return new ResponseEntity<>(makeMap("Error", "No farm"), HttpStatus.FORBIDDEN);
		}
		List<Egg> farmEggs = farmService.findAllFarmEggs();
		
		for (int i = 0; i < farmEggs.size(); i++) {
			Egg farmEgg = farmEggs.get(i);
			if(farmEgg.getEggDays() < 3) {
				farmService.updateEggById(farmEggs.get(i).getId());
			}
			else {
				farmService.deleteFarmEggById(farmEgg.getId());
				farmService.saveFarmChick();
				farmService.huboEggHatch((long) 1);
			}
		}
		
		List<Chick> farmChicks = farmService.findAllFarmChicks();
		
		for (int i = 0; i < farmChicks.size(); i++) {
			Chick farmChick = farmChicks.get(i);
			if(farmChick.getChickDays() < 10) {
				farmService.updateChickById(farmChicks.get(i).getId());
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
		
		Map<String, Object> tomorrowDTO = tomorrowDTO((long) 1);
		return new ResponseEntity<>(tomorrowDTO, HttpStatus.OK);
	}

	/*
	 * @GetMapping("obtener-config") public ResponseEntity<?>
	 * obtenerConfig(@Value("${server.port}") String puerto){ Map<String, String>
	 * json = new HashMap<>(); json.put("texto", texto); json.put("puerto", puerto);
	 * return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK); }
	 */
}
