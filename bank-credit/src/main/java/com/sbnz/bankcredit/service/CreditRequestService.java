package com.sbnz.bankcredit.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sbnz.bankcredit.dto.CreditRequestDTO;
import com.sbnz.bankcredit.dto.RealEstateDTO;
import com.sbnz.bankcredit.mapper.CreditRequestMapper;
import com.sbnz.bankcredit.mapper.RealEstateMapper;
import com.sbnz.bankcredit.model.Answer;
import com.sbnz.bankcredit.model.Client;
import com.sbnz.bankcredit.model.Contract;
import com.sbnz.bankcredit.model.CreditRequest;
import com.sbnz.bankcredit.model.RealEstate;
import com.sbnz.bankcredit.model.User;
import com.sbnz.bankcredit.model.Warrantly;
import com.sbnz.bankcredit.repository.IClientRepository;
import com.sbnz.bankcredit.repository.IContractRepository;
import com.sbnz.bankcredit.repository.IUserRepository;

@Service
public class CreditRequestService {
	
	private final KieContainer kieContainer;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private IContractRepository contractRepository;
	
	@Autowired
	private IClientRepository clientRepository;
	
	@Autowired
	public CreditRequestService(KieContainer kieCont) {
		this.kieContainer = kieCont;
	}
	
	public void executeRules() {
		
	}

	public Answer checkBasicRules(CreditRequestDTO creditReqDTO) {
		User uclient = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Client client = userDetailsService.getClient(uclient);
		CreditRequest cr = CreditRequestMapper.getCreditRequestFromDTO(creditReqDTO);
		
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.getAgenda().getAgendaGroup("basic req rules").setFocus();
		kieSession.insert(cr);
		kieSession.insert(client);
		Collection<Contract> conts = contractRepository.findAll();
		for (Contract con : conts) {
			kieSession.insert(con);
		}
		kieSession.fireAllRules();
		
		Answer answer = null;
		for (Object obj : kieSession.getObjects()) {
			if (obj instanceof Answer) {
				answer = (Answer) obj;
				break;
			}
		}
		kieSession.dispose();
		System.out.println(answer);
		return answer;
	}

	public Answer checkwarrantlyFulfillment(CreditRequest cr) {
		User uclient = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Client client = userDetailsService.getClient(uclient);
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.getAgenda().getAgendaGroup("warrantly").setFocus();
		cr.setClient(client);
		kieSession.insert(cr);
		kieSession.insert(client);
		kieSession.insert(cr.getWarrantly());
		if (cr.getWarrantly().getRealEstate() != null) {
			for (RealEstate re : cr.getWarrantly().getRealEstate()) {
				kieSession.insert(re);
			}
		}
		else if (cr.getWarrantly().getGuarantor() != null) {
			Client user = clientRepository.findOneByJmbg(cr.getWarrantly().getGuarantor().getJmbg()).orElse(null);
			if (user == null) {
				return new Answer(false, "Ne postoji klijent sa unijetim jmbg-om");
			}
			else {
				cr.getWarrantly().setGuarantor(user);
				kieSession.insert(user);
				kieSession.getAgenda().getAgendaGroup("basic req rules").setFocus();
				Collection<Contract> conts = contractRepository.findAll();
				for (Contract con : conts) {
					kieSession.insert(con);
				}
			}
		}
		kieSession.fireAllRules();
		Answer answer = null;
		for (Object obj : kieSession.getObjects()) {
			if (obj instanceof Answer) {
				answer = (Answer) obj;
				break;
			}
		}
		kieSession.dispose();
		
		if (answer.isAccepted() == true) {
			Contract c = new Contract(cr, 0, 0, null, true, cr.getSumOfMoney()); //inicijalizujemo kredit
			answer.setContract(c);
		}
		
		return answer;
	}

//	public Answer checkBasicClientRules(CreditRequestDTO creditReqDTO) {
//		User uclient = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		Client client = userDetailsService.getClient(uclient);
//		Optional<Client> c2 = clientRepository.findById((long) 3);
//		KieSession kieSession = kieContainer.newKieSession("rulesSession");
//		kieSession.getAgenda().getAgendaGroup("reward points").setFocus();
//		
//		Date date = new Date();
//		Calendar c = Calendar.getInstance(); 
//		c.setTime(date);
//		c.add(Calendar.YEAR, -65);
//		date = c.getTime();
//		kieSession.insert(date);
//		kieSession.insert(client);
//		
//		CreditRequest cr = CreditRequestMapper.getCreditRequestFromDTO(creditReqDTO);
//		cr.setClient(client);
//		cr.setWarrantly(new Warrantly(c2.get()));
//		Contract contract = new Contract(cr, 200, 3, null, true, cr.getSumOfMoney() + 10000);
//		kieSession.insert(contract);
//		kieSession.insert(cr);
//		Collection<Contract> conts = contractRepository.findAll();
//		for (Contract con : conts) {
//			kieSession.insert(con);
//		}
//		
//		kieSession.fireAllRules();
//		Answer answer = null;
//		for (Object obj : kieSession.getObjects()) {
//			if (obj instanceof Answer) {
//				answer = (Answer) obj;
//				System.out.println(answer.getDescription());
//				//break;
//			}
//		}
//		kieSession.dispose();
//		return answer;
//	}

	public Answer createContract(Contract cr) {
		User uclient = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Client client = userDetailsService.getClient(uclient);
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.getAgenda().getAgendaGroup("reward points").setFocus();
		cr.setClient(client);
		kieSession.insert(cr);
		kieSession.insert(client);
		cr.getCreditRequest().setClient(client);
		CreditRequest creditRequest = cr.getCreditRequest();
		kieSession.insert(creditRequest);

		Collection<Contract> conts = contractRepository.findAll();
		for (Contract con : conts) {
			kieSession.insert(con);
		}
		kieSession.fireAllRules();
		Answer answer = null;
		for (Object obj : kieSession.getObjects()) {
			if (obj instanceof Answer) {
				answer = (Answer) obj;
				return answer;
			}
		}
		kieSession.dispose();
		
		Date now = new Date();
		Timestamp ts = new Timestamp(now.getTime());
		cr.setSigningDate(ts);
		answer = new Answer(true, "Ponuda", cr);
		
		return answer;
	}

	public Object proba() {
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		Contract c = new Contract();
		c.setId(20L);
		kieSession.insert(c);
		
		int rules = kieSession.fireAllRules();
		System.out.println("num rules: " + rules);
		kieSession.fireAllRules();
		kieSession.dispose();

		return rules;
	}
}
