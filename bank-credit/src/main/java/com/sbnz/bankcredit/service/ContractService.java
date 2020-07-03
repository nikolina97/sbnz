package com.sbnz.bankcredit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sbnz.bankcredit.dto.ContractInfoDTO;
import com.sbnz.bankcredit.mapper.ContractMapper;
import com.sbnz.bankcredit.model.Client;
import com.sbnz.bankcredit.model.Contract;
import com.sbnz.bankcredit.model.User;
import com.sbnz.bankcredit.repository.IClientRepository;
import com.sbnz.bankcredit.repository.IContractRepository;
import com.sbnz.bankcredit.repository.ICreditRequestRepository;

@Service
public class ContractService {
	
	@Autowired
	private IContractRepository contractRepository;
	
	@Autowired
	private ICreditRequestRepository creditRequestRepository;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private IClientRepository clientRepository;

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

		User uclient = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Client client = userDetailsService.getClient(uclient);
		
		client.setMonthlyOutcome(client.getMonthlyOutcome() + contract.getMonthlyPayment());
		contractRepository.save(contract);
		clientRepository.save(client);
		return true;
	}

	public Contract stop(ContractInfoDTO contractDto) {
		Contract c = contractRepository.findById(contractDto.getContractId()).orElse(null);
		c.setActive(false);
		c.setRemainingSum(0);
		contractRepository.save(c);
		return c;
	}

}
