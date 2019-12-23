package com.formacionbdi.springboot.app.farm.models.service;

import java.util.List;

import com.formacionbdi.springboot.app.commons.models.entity.Bird;
import com.formacionbdi.springboot.app.commons.models.entity.Egg;
import com.formacionbdi.springboot.app.commons.models.entity.Farm;
import com.formacionbdi.springboot.app.farm.models.dto.ChickDTO;
import com.formacionbdi.springboot.app.farm.models.dto.EggDTO;

public interface IFarmService {
	
	/*** METODOS FARM ***/
	
	public double getMoney(Long farmID);
	public void earnMoneyEgg(Long farmID);
	public void spendMoneyEgg(Long farmID);
	public void earnMoneyChick(Long farmID);
	public void spendMoneyChick(Long farmID);
	
	public int getEggHatches(Long farmID);
	public int getLayedEggs(Long farmID);
	public int getCasualties(Long farmID);
	
	public void huboEggHatch(Long farmID);
	public void huboLayedEgg(Long farmID);
	public void huboCasualty(Long farmID);
	
	public Farm findFarmById(Long farmID);
	public List<Farm> findAllFarms();
	public Farm saveFarm(Farm farm);
	
	public Egg findEggById(Long eggID);
	public Bird findChickById(Long chickID);
	
	/*** METODOS CONSUMIDOS DE EGG Y CHICK ***/
	
	public List<EggDTO> findAllFarmEggDTOS();
	public List<ChickDTO> findAllFarmChickDTOS();
	
	public Egg saveFarmEgg();
	public Bird saveFarmChick();
	
	public void deleteFarmEggById(Long eggID);
	public void deleteFarmChickById(Long chickID);
	
	public Egg updateEggById(Long eggID);
	public Bird updateChickById(Long chickID);
}
