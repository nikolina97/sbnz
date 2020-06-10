package com.sbnz.bankcredit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.bankcredit.dto.ContractInfoDTO;
import com.sbnz.bankcredit.mapper.ContractMapper;
import com.sbnz.bankcredit.model.Contract;
import com.sbnz.bankcredit.repository.IContractRepository;
import com.sbnz.bankcredit.repository.ICreditRequestRepository;

@Service
public class ContractService {
	
	@Autowired
	private IContractRepository contractRepository;
	
	@Autowired
	private ICreditRequestRepository creditRequestRepository;

	public List<ContractInfoDTO> getAllContracts() {
		
		List<ContractInfoDTO> credits = new ArrayList<ContractInfoDTO>();
		
		List<Contract> contracts = contractRepository.findAll();
		for (Contract contract : contracts) {
			ContractInfoDTO cDTO = ContractMapper.toDTO(contract);
			credits.add(cDTO);
		}
		return credits;
	}

	public boolean addContract(Contract contract) {
//		creditRequestRepository.save(contract.getCreditRequest());
		contractRepository.save(contract);
		return true;
	}

}
