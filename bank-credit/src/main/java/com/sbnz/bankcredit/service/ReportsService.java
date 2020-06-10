package com.sbnz.bankcredit.service;


import java.util.Collection;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.bankcredit.dto.ActiveClientsDTO;
import com.sbnz.bankcredit.dto.ClientDTO;
import com.sbnz.bankcredit.dto.ContractList;
import com.sbnz.bankcredit.dto.CreditCountDTO;
import com.sbnz.bankcredit.model.Client;
import com.sbnz.bankcredit.model.Contract;
import com.sbnz.bankcredit.repository.IClientRepository;
import com.sbnz.bankcredit.repository.IContractRepository;

@Service
public class ReportsService {
	
	private final KieContainer kieContainer;
	
	@Autowired
	private IContractRepository contractRepository;
	
	@Autowired
	private IClientRepository clientRepository;
	
	@Autowired
	public ReportsService(KieContainer kieCont) {
		this.kieContainer = KieServices.Factory.get().getKieClasspathContainer();
	}
	
	public void executeRules() {
		
	}
	
	public CreditCountDTO getCountOfCreditsByType() {
		
		Collection<Contract> contracts = contractRepository.findAllBySigned(true);
		System.out.println(contracts.size());
		KieSession kieSession = kieContainer.newKieSession("reportsSession");
		for (Contract contract : contracts) {
			kieSession.insert(contract);
		}
		ClientDTO c = new ClientDTO();
		kieSession.insert(c);
		kieSession.fireAllRules();
				
		for (Object obj : kieSession.getObjects()) {
			if (obj instanceof CreditCountDTO) {
				System.out.println("ccdto");
				return (CreditCountDTO) obj;
			}
			if (obj instanceof Contract) {
				System.out.println("contradt");
			}
		}
		
		kieSession.dispose();

		return null;
	}

	public ContractList getLargestLoan() {
		Collection<Contract> contracts = contractRepository.findAllBySigned(true);
		KieSession kieSession = kieContainer.newKieSession("reportsSession");
		for (Contract contract : contracts) {
			kieSession.insert(contract);
		}		
		kieSession.fireAllRules();
		ContractList contractList = null;
		for (Object obj : kieSession.getObjects()) {
			if (obj instanceof ContractList) {
				if (((ContractList) obj).getMax() == true) {
					contractList = (ContractList) obj;
					break;
				}
			}
		}
		kieSession.dispose();
		return contractList;
	}

	public ContractList getSmallestLoan() {
		Collection<Contract> contracts = contractRepository.findAllBySigned(true);
		KieSession kieSession = kieContainer.newKieSession("reportsSession");
		for (Contract contract : contracts) {
			kieSession.insert(contract);
		}		
		kieSession.fireAllRules();
		ContractList contractList = null;
		for (Object obj : kieSession.getObjects()) {
			if (obj instanceof ContractList) {
				if (((ContractList) obj).getMax() == false) {
					contractList = (ContractList) obj;
					break;
				}
			}
		}
		kieSession.dispose();
		return contractList;
	}

	public ActiveClientsDTO getMostActiveClients() {
		
		ActiveClientsDTO activeClients = new ActiveClientsDTO();
		List<Client> clients = clientRepository.findAll();
		Collection<Contract> contracts = contractRepository.findAllBySigned(true);
		KieSession kieSession = kieContainer.newKieSession("reportsSession");
		
		for (Client client : clients) {
			kieSession.insert(client);
		}
		for (Contract contract : contracts) {
			kieSession.insert(contract);
		}
		kieSession.insert(activeClients);
		kieSession.fireAllRules();
		
		return activeClients;
		
		
	}
}
