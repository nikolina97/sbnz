package com.sbnz.bankcredit.events;

//import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
//@Expires("2h30m")
public class TransactionEvent {
	
	private String username;
	private String accountNumberFrom;
	private String accountNumberTo;
	private double totalAmount;
	
	public TransactionEvent() {
		super();
	}

	public TransactionEvent(String username, String accountNumberFrom, String accountNumberTo, double totalAmount) {
		super();
		this.username = username;
		this.accountNumberFrom = accountNumberFrom;
		this.accountNumberTo = accountNumberTo;
		this.totalAmount = totalAmount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccountNumberFrom() {
		return accountNumberFrom;
	}

	public void setAccountNumberFrom(String accountNumberFrom) {
		this.accountNumberFrom = accountNumberFrom;
	}

	public String getAccountNumberTo() {
		return accountNumberTo;
	}

	public void setAccountNumberTo(String accountNumberTo) {
		this.accountNumberTo = accountNumberTo;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
