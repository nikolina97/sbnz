package com.sbnz.bankcredit.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "real_estate_id")
	private RealEstate realEstate;

	public Warrantly() {
		super();
		this.guarantor = null;
		this.realEstate = null;
	}

	public Warrantly(Client guarantor) {
		super();
		this.guarantor = guarantor;
		this.realEstate = null;
	}
	
	public Warrantly(RealEstate realEstate) {
		super();
		this.realEstate = realEstate;
		this.guarantor = null;
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

	public RealEstate getRealEstate() {
		return realEstate;
	}

	public void setRealEstate(RealEstate realEstate) {
		this.realEstate = realEstate;
	}
	
}
