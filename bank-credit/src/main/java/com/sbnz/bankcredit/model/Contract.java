package com.sbnz.bankcredit.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "credit_request")
public class Contract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "credit_request_id")
	private CreditRequest creditRequest;
	
	@Column(name = "monthly_payment")
	private double monthlyPayment;
	
	@Column(name = "interest")
	private double interest;
	
	@Column(name = "signing_date")
	private Timestamp signingDate;

	public Contract() {
		super();
	}

	public Contract(CreditRequest creditRequest, double monthlyPayment, double interest, Timestamp signingDate) {
		super();
		this.creditRequest = creditRequest;
		this.monthlyPayment = monthlyPayment;
		this.interest = interest;
		this.signingDate = signingDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CreditRequest getCreditRequest() {
		return creditRequest;
	}

	public void setCreditRequest(CreditRequest creditRequest) {
		this.creditRequest = creditRequest;
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
	
}
