package com.formacionbdi.springboot.app.commons.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chicks")
public class Chick implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "chick_days")
	private int chickDays;

	/*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "chick_id")
	@Column(name = "chick_eggs")
	private List<Egg> chickEggs;*/

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "farm_id")
	private Farm chickFarm;

	public Farm getChickFarm() {
		return chickFarm;
	}

	public void setChickFarm(Farm chickFarm) {
		this.chickFarm = chickFarm;
	}*/

	public void setId(Long id) {
		this.id = id;
	}

	public Chick() {
		//chickEggs = new ArrayList<Egg>();
	}

	public Long getId() {
		return id;
	}

	public int getChickDays() {
		return chickDays;
	}

	public void setChickDays(int chickDays) {
		this.chickDays = chickDays;
	}

	/*public List<Egg> getChickEggs() {
		return chickEggs;
	}

	public void setChickEggs(List<Egg> chickEggs) {
		this.chickEggs = chickEggs;
	}*/

	private static final long serialVersionUID = -5870030148959389696L;
}
