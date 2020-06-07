package com.sbnz.bankcredit.dto;

import com.sbnz.bankcredit.model.Account;

public class ClientDTO {
		
	private long clientId; 
	
	private Account account;
	
	private double monthlyIncome;
	
	private double monthlyOutcome;
	
	private int rewardPoints;

	public ClientDTO() {
		super();
	}

	public ClientDTO(long clientId, Account account, double monthlyIncome, double monthlyOutcome, int rewardPoints) {
		super();
		this.clientId = clientId;
		this.account = account;
		this.monthlyIncome = monthlyIncome;
		this.monthlyOutcome = monthlyOutcome;
		this.rewardPoints = rewardPoints;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public double getMonthlyOutcome() {
		return monthlyOutcome;
	}

	public void setMonthlyOutcome(double monthlyOutcome) {
		this.monthlyOutcome = monthlyOutcome;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	
}
