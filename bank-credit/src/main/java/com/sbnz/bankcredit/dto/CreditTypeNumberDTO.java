package com.sbnz.bankcredit.dto;

import com.sbnz.bankcredit.model.CreditType;

public class CreditTypeNumberDTO {
	
	private int count;
	private CreditType creditType;
	
	public CreditTypeNumberDTO() {
		super();
	}

	public CreditTypeNumberDTO(int count, CreditType creditType) {
		super();
		this.count = count;
		this.creditType = creditType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public CreditType getCreditType() {
		return creditType;
	}

	public void setCreditType(CreditType creditType) {
		this.creditType = creditType;
	}
	
}
