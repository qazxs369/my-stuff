package com.formacionbdi.springboot.app.farm.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.commons.models.entity.Chick;
import com.formacionbdi.springboot.app.commons.models.entity.Egg;
import com.formacionbdi.springboot.app.commons.models.entity.Farm;
import com.formacionbdi.springboot.app.farm.clientes.ChickClienteRest;
import com.formacionbdi.springboot.app.farm.clientes.EggClienteRest;
import com.formacionbdi.springboot.app.farm.models.dao.FarmDao;
import com.formacionbdi.springboot.app.farm.models.dto.ChickDTO;
import com.formacionbdi.springboot.app.farm.models.dto.EggDTO;

@Service("serviceFeign")

@Primary
public class FarmServiceFeign implements IFarmService {
	
	@Autowired
	private FarmDao farmDao;

	@Autowired
	private EggClienteRest clienteEggFeign;
	
	@Autowired
	private ChickClienteRest clienteChickFeign;
	
	@Override
	public List<EggDTO> findAllFarmEggDTOS() {
		return clienteEggFeign.listar();
	}

	@Override
	public List<ChickDTO> findAllFarmChickDTOS() {
		return clienteChickFeign.listar();
	}

	@Override
	public Egg saveFarmEgg() {
		return clienteEggFeign.comprar();
	}

	@Override
	public Chick saveFarmChick() {
		return clienteChickFeign.comprar();
	}

	@Override
	public void deleteFarmEggById(Long eggID) {
		clienteEggFeign.vender(eggID);
	}
	
	@Override
	public void deleteFarmChickById(Long chickID) {
		clienteChickFeign.vender(chickID);
	}

	@Override
	public double getMoney(Long farmID) {
		return farmDao.findById(farmID).orElse(null).getMoney();
	}

	@Override
	public Farm findFarmById(Long farmID) {
		return farmDao.findById(farmID).orElse(null);
	}

	@Override
	public List<Farm> findAllFarms() {
		return (List<Farm>) farmDao.findAll();
	}

	@Override
	public Farm saveFarm(Farm farm) {
		return farmDao.save(farm);
	}

	@Override
	public void earnMoneyEgg(Long farmID) {
		Farm farm = farmDao.findById(farmID).get();
		double earnings = farm.getMoney() + 10.0; 
		farm.setMoney(earnings);
		farm.huboVentaEgg();
		farmDao.save(farm);
	}

	@Override
	public void spendMoneyEgg(Long farmID) {
		Farm farm = farmDao.findById(farmID).get();
		double expenses = farm.getMoney() - 10.0; 
		farm.setMoney(expenses);
		farm.huboCompraEgg();
		farmDao.save(farm);
	}

	@Override
	public void earnMoneyChick(Long farmID) {
		Farm farm = farmDao.findById(farmID).get();
		double earnings = farm.getMoney() + 100.0; 
		farm.setMoney(earnings);
		farm.huboVentaChick();
		farmDao.save(farm);
	}

	@Override
	public void spendMoneyChick(Long farmID) {
		Farm farm = farmDao.findById(farmID).get();
		double expenses = farm.getMoney() - 100.0; 
		farm.setMoney(expenses);
		farm.huboCompraChick();
		farmDao.save(farm);
	}

	@Override
	public Egg updateEggById(Long eggID) {
		return clienteEggFeign.eggUpdate(eggID);
	}

	@Override
	public Chick updateChickById(Long chickID) {
		return clienteChickFeign.chickUpdate(chickID);
	}

	@Override
	public int getEggHatches(Long farmID) {
		return farmDao.findById(farmID).orElse(null).getEggHatches();
	}

	@Override
	public int getLayedEggs(Long farmID) {
		return farmDao.findById(farmID).orElse(null).getLayedEggs();
	}

	@Override
	public int getCasualties(Long farmID) {
		return farmDao.findById(farmID).orElse(null).getCasualties();
	}

	@Override
	public void huboEggHatch(Long farmID) {
		Farm farm = farmDao.findById((long) 1).get();
		farm.setEggHatches(getEggHatches(farmID) + 1);
		farmDao.save(farm);
	}

	@Override
	public void huboLayedEgg(Long farmID) {
		Farm farm = farmDao.findById((long) 1).get();
		farm.setLayedEggs(getLayedEggs(farmID) + 1);
		farmDao.save(farm);
	}

	@Override
	public void huboCasualty(Long farmID) {
		Farm farm = farmDao.findById((long) 1).get();
		farm.setCasualties(getCasualties(farmID) + 1);
		farmDao.save(farm);
	}

	@Override
	public Egg findEggById(Long eggID) {
		return clienteEggFeign.detalle(eggID);
	}

	@Override
	public Chick findChickById(Long chickID) {
		return clienteChickFeign.detalle(chickID);
	}

	

}
