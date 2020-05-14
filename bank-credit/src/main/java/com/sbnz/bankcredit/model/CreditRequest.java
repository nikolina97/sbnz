package com.sbnz.bankcredit.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "credit_request")
public class CreditRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id")
	private Client client;
	
	@Column(name = "sum_of_money")
	private double sumOfMoney;
	
	@Column(name = "monthly_payment_period")
	private int monthlyPaymentPeriod;
	
	@Column(name = "credit_type")
	private CreditType creditType;
	
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "warrantly_id")
	private Warrantly warrantly;

	public CreditRequest() {
		super();
	}

	public CreditRequest(Client client, double sumOfMoney, int monthlyPaymentPeriod, CreditType creditType,
			Warrantly warrantly) {
		super();
		this.client = client;
		this.sumOfMoney = sumOfMoney;
		this.monthlyPaymentPeriod = monthlyPaymentPeriod;
		this.creditType = creditType;
		this.warrantly = warrantly;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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

	public Warrantly getWarrantly() {
		return warrantly;
	}

	public void setWarrantly(Warrantly warrantly) {
		this.warrantly = warrantly;
	}
	
}
