package com.sbnz.bankcredit.dto;

public class ConditionDTO {

	private int totalPoints;
	private int totalConditions;
	private int fulfilledConditions;
	private double percent;
	
	public ConditionDTO() {
		super();
	}
	public ConditionDTO(int totalPoints, int totalConditions, int fulfilledConditions) {
		super();
		this.totalPoints = totalPoints;
		this.totalConditions = totalConditions;
		this.fulfilledConditions = fulfilledConditions;
		this.percent = getPercent();
	}
	public int getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	public int getTotalConditions() {
		return totalConditions;
	}
	public void setTotalConditions(int totalConditions) {
		this.totalConditions = totalConditions;
	}
	public int getFulfilledConditions() {
		return fulfilledConditions;
	}
	public void setFulfilledConditions(int fulfilledConditions) {
		this.fulfilledConditions = fulfilledConditions;
	}
	public double getPercent() {
		percent = fulfilledConditions*100/totalConditions;
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	
}
