package com.formacionbdi.springboot.app.farm.models.service;

import java.util.List;

import com.formacionbdi.springboot.app.commons.models.entity.Chick;
import com.formacionbdi.springboot.app.commons.models.entity.Egg;
import com.formacionbdi.springboot.app.commons.models.entity.Farm;

public interface FarmService {
	
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
	
	public Farm findById(Long farmID);
	public List<Farm> findAllFarms();
	public Farm saveFarm(Farm farm);
	
	/*** METODOS CONSUMIDOS DE EGG Y CHICK ***/
	
	public List<Egg> findAllFarmEggs();
	public List<Chick> findAllFarmChicks();
	
	public Egg saveFarmEgg();
	public Chick saveFarmChick();
	
	public void deleteFarmEggById(Long eggID);
	public void deleteFarmChickById(Long chickID);
	
	public Egg updateEggById(Long eggID);
	public Chick updateChickById(Long chickID);
}
