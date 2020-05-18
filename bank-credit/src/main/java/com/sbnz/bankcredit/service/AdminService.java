package com.sbnz.bankcredit.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.bankcredit.model.Answer;
import com.sbnz.bankcredit.model.CreditRequest;
import com.sbnz.bankcredit.model.RealEstate;
import com.sbnz.bankcredit.model.RealEstateType;
import com.sbnz.bankcredit.model.Warrantly;

@Service
public class AdminService {

	private final KieContainer kieContainer;
	
	@Autowired
	public AdminService(KieContainer kieCont) {
		this.kieContainer = kieCont;
	}
	
	public String fireRules() { 
		RealEstate re = new RealEstate(2, RealEstateType.House, 20);
		CreditRequest c = new CreditRequest();
		c.setSumOfMoney(200000);
		c.setMonthlyPaymentPeriod(15);
//		c.setWarrantly(new Warrantly(re));
		System.out.println(("Prvo: " + re.getPrice()));
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
//		kieSession.insert(re);
		kieSession.insert(c);
		kieSession.insert(c.getWarrantly());
		
		Answer answer = null;
		for (Object obj : kieSession.getObjects()) {
			if (obj instanceof Answer) {
				answer = (Answer) obj;
				break;
			}
		}
		kieSession.dispose();
		return answer.getDescription();
		//		System.out.println(re.getPrice());
	}
}
