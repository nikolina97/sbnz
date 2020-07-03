package com.sbnz.bankcredit.events;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
public class AnnuityEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double loanInstallment;
	
	private String accountNumber;
	
	private double balance;
	
	private String username;
	
	private Date executionTime;
	
	private Boolean doneAnnuity;

	public AnnuityEvent() {
		super();
	}

	public AnnuityEvent(double loanInstallment, String accountNumber, double balance, String username, Date executionTime, Boolean doneAnnuity) {
		super();
		this.loanInstallment = loanInstallment;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.executionTime = executionTime;
		this.username = username;
		this.doneAnnuity = doneAnnuity;
	}

	public double getLoanInstallment() {
		return loanInstallment;
	}

	public void setLoanInstallment(double loanInstallment) {
		this.loanInstallment = loanInstallment;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}

	public Boolean getDoneAnnuity() {
		return doneAnnuity;
	}

	public void setDoneAnnuity(Boolean doneAnnuity) {
		this.doneAnnuity = doneAnnuity;
	}
}
