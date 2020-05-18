package com.sbnz.bankcredit.mapper;

import com.sbnz.bankcredit.dto.CreditRequestDTO;
import com.sbnz.bankcredit.model.CreditRequest;

public class CreditRequestMapper {
	
	public static CreditRequest getCreditRequestFromDTO(CreditRequestDTO crDTO) {
		CreditRequest cr = new CreditRequest();
		cr.setSumOfMoney(crDTO.getSumOfMoney());
		cr.setMonthlyPaymentPeriod(crDTO.getMonthlyPaymentPeriod());
		cr.setCreditType(crDTO.getCreditType());
		return cr;
	}
}
