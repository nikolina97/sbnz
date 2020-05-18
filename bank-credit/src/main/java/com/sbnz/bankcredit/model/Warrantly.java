package com.sbnz.bankcredit.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "warrantly")
public class Warrantly {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "guarantor")
	private Client guarantor;
	
	@Column(name = "insurance")
	private boolean insurance;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "warrantly_id")
	private List<RealEstate> realEstate;

	public Warrantly() {
		super();
		this.guarantor = null;
		this.realEstate = null;
		this.insurance = false;
	}

	public Warrantly(Client guarantor) {
		super();
		this.guarantor = guarantor;
		this.realEstate = null;
		this.insurance = false;
	}
	
	public Warrantly(List<RealEstate> realEstate) {
		super();
		this.realEstate = realEstate;
		this.guarantor = null;
		this.insurance = false;
	}

	public Warrantly(boolean insurance) {
		super();
		this.guarantor = null;
		this.realEstate = null;
		this.insurance = insurance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Client getGuarantor() {
		return guarantor;
	}

	public void setGuarantor(Client guarantor) {
		this.guarantor = guarantor;
	}

	public List<RealEstate> getRealEstate() {
		return realEstate;
	}

	public void setRealEstate(List<RealEstate> realEstate) {
		this.realEstate = realEstate;
	}

	public boolean isInsurance() {
		return insurance;
	}

	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}
	
}
