package com.sbnz.bankcredit.dto;

public class ContractDTO {

	private long contractId;
	
	private double basicInterest;
	
	private int rewardPoints;

	public ContractDTO(long contractId, double basicInterest, int rewardPoints) {
		super();
		this.contractId = contractId;
		this.basicInterest = basicInterest;
		this.rewardPoints = rewardPoints;
	}

	public ContractDTO() {
		super();
	}

	public long getContractId() {
		return contractId;
	}

	public void setContractId(long contractId) {
		this.contractId = contractId;
	}

	public double getBasicInterest() {
		return basicInterest;
	}

	public void setBasicInterest(double basicInterest) {
		this.basicInterest = basicInterest;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	
}
