package com.formacionbdi.springboot.app.commons.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "farms")
public class Farm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double money;
	private int comprasEgg;
	private int ventasEgg;
	private int comprasChick;
	private int ventasChick;
	private int eggHatches;
	private int layedEggs;
	private int casualties;

	public int getEggHatches() {
		return eggHatches;
	}

	public void setEggHatches(int eggHatches) {
		this.eggHatches = eggHatches;
	}

	public int getLayedEggs() {
		return layedEggs;
	}

	public void setLayedEggs(int layedEggs) {
		this.layedEggs = layedEggs;
	}

	public int getCasualties() {
		return casualties;
	}

	public void setCasualties(int casualties) {
		this.casualties = casualties;
	}

	public Farm() {
	}

	public Farm(double money, int comprasEgg, int ventasEgg, int comprasChick, int ventasChick, int eggHatches,
			int layedEggs, int casualties) {
		this.money = money;
		this.comprasEgg = comprasEgg;
		this.ventasEgg = ventasEgg;
		this.comprasChick = comprasChick;
		this.ventasChick = ventasChick;
		this.eggHatches = eggHatches;
		this.layedEggs = layedEggs;
		this.casualties = casualties;
	}

	public int getComprasChick() {
		return comprasChick;
	}

	public void setComprasChick(int comprasChick) {
		this.comprasChick = comprasChick;
	}

	public int getVentasChick() {
		return ventasChick;
	}

	public void setVentasChick(int ventasChick) {
		this.ventasChick = ventasChick;
	}

	public void setVentasEgg(int ventasEgg) {
		this.ventasEgg = ventasEgg;
	}

	public int getComprasEgg() {
		return comprasEgg;
	}

	public void setComprasEgg(int comprasEgg) {
		this.comprasEgg = comprasEgg;
	}

	public int getVentasEgg() {
		return ventasEgg;
	}

	public Long getId() {
		return id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public void huboVentaEgg() {
		this.ventasEgg++;
	}

	public void huboCompraEgg() {
		this.comprasEgg++;
	}

	public void huboVentaChick() {
		this.ventasChick++;
	}

	public void huboCompraChick() {
		this.comprasChick++;
	}
}
