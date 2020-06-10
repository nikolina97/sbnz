package com.sbnz.bankcredit.dto;

import java.io.Serializable;
import java.util.List;

public class CreditCountDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<CreditTypeNumberDTO> list;

	public CreditCountDTO(List<CreditTypeNumberDTO> list) {
		super();
		this.list = list;
	}

	public CreditCountDTO() {
		super();
	}

	public List<CreditTypeNumberDTO> getList() {
		return list;
	}

	public void setList(List<CreditTypeNumberDTO> list) {
		this.list = list;
	}
	
}
