package com.sbnz.bankcredit.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity  
@Table(name="client")  
//@AttributeOverrides({  
//    @AttributeOverride(name="id", column=@Column(name="id")),  
//    @AttributeOverride(name="username", column=@Column(name="username")),
//    @AttributeOverride(name="password", column=@Column(name="password")),
//    @AttributeOverride(name="first_name", column=@Column(name="first_name")),
//    @AttributeOverride(name="last_name", column=@Column(name="last_name")),
//}) 
public class Client extends User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "date_of_birth")
	private Timestamp dateOfBirth;
	
	@Column(name = "jmbg")
	private String jmbg;
	
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private Account account;
	
	@Column(name = "monthly_income")
	private double monthlyIncome;
	
	@Column(name = "monthly_outcome")
	private double monthlyOutcome;
	
	@Column(name = "account_opening_date")
	private Timestamp accountOpeningDate;
	
	@Column(name = "reward_points")
	private int rewardPoints;
	
	public Client() {
		super();
	}

	public Client(String username, String password, String firstName, String lastName, List<Authority> authorities) {
		super(username, password, firstName, lastName, authorities);
	}

	public Client(String username, String password, String firstName, String lastName, List<Authority> authorities,
			Timestamp dateOfBirth, String jmbg, Account account, double monthlyIncome, double monthlyOutcome,
			Timestamp accountOpeningDate, int rewardPoints) {
		super(username, password, firstName, lastName, authorities);
		this.dateOfBirth = dateOfBirth;
		this.jmbg = jmbg;
		this.account = account;
		this.monthlyIncome = monthlyIncome;
		this.monthlyOutcome = monthlyOutcome;
		this.accountOpeningDate = accountOpeningDate;
		this.rewardPoints = rewardPoints;
	}

	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
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

	public Timestamp getAccountOpeningDate() {
		return accountOpeningDate;
	}

	public void setAccountOpeningDate(Timestamp accountOpeningDate) {
		this.accountOpeningDate = accountOpeningDate;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	
}
