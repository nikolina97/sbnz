package com.sbnz.bankcredit.dto;

import com.sbnz.bankcredit.model.CreditType;

public class CreditRequestDTO {
	
	private double sumOfMoney;
	
	private int monthlyPaymentPeriod;
	
	private CreditType creditType;

	public CreditRequestDTO() {
		super();
	}

	public CreditRequestDTO(double sumOfMoney, int monthlyPaymentPeriod, CreditType creditType) {
		super();
		this.sumOfMoney = sumOfMoney;
		this.monthlyPaymentPeriod = monthlyPaymentPeriod;
		this.creditType = creditType;
	}

	public double getSumOfMoney() {
		return sumOfMoney;
	}

	public void setSumOfMoney(double sumOfMoney) {
		this.sumOfMoney = sumOfMoney;
	}

	public int getMonthlyPaymentPeriod() {
		return monthlyPaymentPeriod;
	}

	public void setMonthlyPaymentPeriod(int monthlyPaymentPeriod) {
		this.monthlyPaymentPeriod = monthlyPaymentPeriod;
	}

	public CreditType getCreditType() {
		return creditType;
	}

	public void setCreditType(CreditType creditType) {
		this.creditType = creditType;
	}
	
}
