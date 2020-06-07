package com.sbnz.bankcredit.dto;

import java.sql.Timestamp;

import com.sbnz.bankcredit.model.Account;

public class ContractInfoDTO {

	private long contractId;
	
	private Account account;
	
	private String clientFirstName;
	
	private String clientLasName;
	
	private String jmbg;
	
	private double monthlyPayment;
	
	private double interest;
	
	private Timestamp signingDate;
	
	private double remainingSum;

	public ContractInfoDTO() {
		super();
	}

	public ContractInfoDTO(long contractId, Account account, String clientFirstName, String clientLasName, String jmbg,
			double monthlyPayment, double interest, Timestamp signingDate, double remainingSum) {
		super();
		this.contractId = contractId;
		this.account = account;
		this.clientFirstName = clientFirstName;
		this.clientLasName = clientLasName;
		this.jmbg = jmbg;
		this.monthlyPayment = monthlyPayment;
		this.interest = interest;
		this.signingDate = signingDate;
		this.remainingSum = remainingSum;
	}

	public long getContractId() {
		return contractId;
	}

	public void setContractId(long contractId) {
		this.contractId = contractId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getClientFirstName() {
		return clientFirstName;
	}

	public void setClientFirstName(String clientFirstName) {
		this.clientFirstName = clientFirstName;
	}

	public String getClientLasName() {
		return clientLasName;
	}

	public void setClientLasName(String clientLasName) {
		this.clientLasName = clientLasName;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public Timestamp getSigningDate() {
		return signingDate;
	}

	public void setSigningDate(Timestamp signingDate) {
		this.signingDate = signingDate;
	}

	public double getRemainingSum() {
		return remainingSum;
	}

	public void setRemainingSum(double remainingSum) {
		this.remainingSum = remainingSum;
	
	}
	
}
