package com.sbnz.bankcredit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name="real_estate") 
public class RealEstate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "zone")
	private int zone;
	
	@Column(name = "real_estate_type")
	private RealEstateType realEstateType;

	public RealEstate() {
		super();
	}

	public RealEstate(int zone, RealEstateType realEstateType) {
		super();
		this.zone = zone;
		this.realEstateType = realEstateType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
	}

	public RealEstateType getRealEstateType() {
		return realEstateType;
	}

	public void setRealEstateType(RealEstateType realEstateType) {
		this.realEstateType = realEstateType;
	}
	
}
