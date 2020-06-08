package com.sbnz.bankcredit.events;

import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class RaisingMoneyEvent {
	
	private String username;
	private String accountNumber;
	private double amount;
	
	public RaisingMoneyEvent() {
		super();
	}
	public RaisingMoneyEvent(String username, String accountNumber, double amount) {
		super();
		this.username = username;
		this.accountNumber = accountNumber;
		this.amount = amount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
