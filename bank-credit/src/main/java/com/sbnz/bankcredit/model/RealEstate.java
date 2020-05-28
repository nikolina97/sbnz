package com.sbnz.bankcredit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "real_estate_type")
	private RealEstateType realEstateType;
	
	@Column(name = "square_footage")
	private int squareFootage;
	
	@Column(name = "price")
	private double price;
	
	public RealEstate() {
		super();
	}

	public RealEstate(int zone, RealEstateType realEstateType, int squareFootage) {
		super();
		this.zone = zone;
		this.realEstateType = realEstateType;
		this.squareFootage = squareFootage;
	}

	public RealEstate(int zone, RealEstateType realEstateType, int squareFootage, double price) {
		super();
		this.zone = zone;
		this.realEstateType = realEstateType;
		this.squareFootage = squareFootage;
		this.price = price;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSquareFootage() {
		return squareFootage;
	}

	public void setSquareFootage(int squareFootage) {
		this.squareFootage = squareFootage;
	}
	
}
