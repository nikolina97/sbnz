package com.sbnz.bankcredit.mapper;

import com.sbnz.bankcredit.dto.ContractInfoDTO;
import com.sbnz.bankcredit.model.Contract;

public class ContractMapper {
		
	public static ContractInfoDTO toDTO(Contract contract) {
		ContractInfoDTO contractInfoDTO = new ContractInfoDTO(contract.getId(), contract.getClient().getAccount(), contract.getClient().getFirstName(), 
				contract.getClient().getLastName(), contract.getClient().getJmbg(), contract.getMonthlyPayment(), contract.getInterest(), contract.getSigningDate(), contract.getRemainingSum());
		return contractInfoDTO;
	}
	
}
