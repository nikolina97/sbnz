package com.sbnz.bankcredit.dto;

import java.util.List;

import com.sbnz.bankcredit.model.Contract;

public class ContractList {
	
	private List<Contract> contracts;
	private Boolean max;

	public ContractList() {
		super();
	}

	public ContractList(List<Contract> contracts, Boolean max) {
		super();
		this.contracts = contracts;
		this.max = max;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public Boolean getMax() {
		return max;
	}

	public void setMax(Boolean max) {
		this.max = max;
	}

}
