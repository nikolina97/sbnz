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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contract")
public class Contract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "credit_request_id")
	private CreditRequest creditRequest;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
	private Client client;
	
	@Column(name = "monthly_payment")
	private double monthlyPayment;
	
	@Column(name = "interest")
	private double interest;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "signed")
	private boolean signed;
	
	@Column(name = "signing_date")
	private Timestamp signingDate;
	
	@Column(name = "remaining_sum")
	private double remainingSum;

	public Contract() {
		super();
		this.signed = false;
	}

	public Contract(CreditRequest creditRequest, double monthlyPayment, double interest, Timestamp signingDate, boolean active, double remSum) {
		super();
		this.creditRequest = creditRequest;
		this.client = creditRequest.getClient();
		this.monthlyPayment = monthlyPayment;
		this.interest = interest;
		this.signingDate = signingDate;
		this.active = active;
		this.remainingSum = remSum;
		this.signed = false;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public double getRemainingSum() {
		return remainingSum;
	}

	public void setRemainingSum(double remainingSum) {
		this.remainingSum = remainingSum;
	}

	public boolean isSigned() {
		return signed;
	}

	public void setSigned(boolean signed) {
		this.signed = signed;
	}
	
}
