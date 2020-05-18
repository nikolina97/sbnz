package com.sbnz.bankcredit.dto;

import com.sbnz.bankcredit.model.RealEstateType;

public class RealEstateDTO {
	
	private int zone;
	
	private RealEstateType realEstateType;
	
	private int squareFootage;

	public RealEstateDTO() {
		super();
	}

	public RealEstateDTO(int zone, RealEstateType realEstateType, int squareFootage) {
		super();
		this.zone = zone;
		this.realEstateType = realEstateType;
		this.squareFootage = squareFootage;
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

	public int getSquareFootage() {
		return squareFootage;
	}

	public void setSquareFootage(int squareFootage) {
		this.squareFootage = squareFootage;
	}
	
}
