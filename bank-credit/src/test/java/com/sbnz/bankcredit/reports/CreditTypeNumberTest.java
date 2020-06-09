package com.sbnz.bankcredit.reports;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import java.util.Collection;

import org.drools.core.ClassObjectFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sbnz.bankcredit.dto.ContractList;
import com.sbnz.bankcredit.dto.CreditCountDTO;
import com.sbnz.bankcredit.dto.CreditTypeNumberDTO;
import com.sbnz.bankcredit.events.ClientWarningEvent;
import com.sbnz.bankcredit.model.Answer;
import com.sbnz.bankcredit.model.Contract;
import com.sbnz.bankcredit.model.CreditRequest;
import com.sbnz.bankcredit.model.CreditType;

public class CreditTypeNumberTest {
	
	@Test
    public void test() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kieSession = kContainer.newKieSession("reportsSession");
		run(kieSession);
    }
	
	public void run(KieSession ksession) {
		
		CreditRequest cr1 = new CreditRequest();
		cr1.setCreditType(CreditType.Housing);
		cr1.setSumOfMoney(50000);
		
		CreditRequest cr2 = new CreditRequest();
		cr2.setCreditType(CreditType.Investment);
		cr2.setSumOfMoney(52000);
		
		CreditRequest cr3 = new CreditRequest();
		cr3.setCreditType(CreditType.Investment);
		cr3.setSumOfMoney(100000);
		
		CreditRequest cr4 = new CreditRequest();
		cr4.setCreditType(CreditType.Housing);
		cr4.setSumOfMoney(44000);
		
		CreditRequest cr5 = new CreditRequest();
		cr5.setCreditType(CreditType.Consumer);
		cr5.setSumOfMoney(20000);
		
		CreditRequest cr6 = new CreditRequest();
		cr6.setCreditType(CreditType.Housing);
		cr6.setSumOfMoney(90000);
		
		Contract c1 = new Contract();
		c1.setCreditRequest(cr1);
		c1.setSigned(true);
		c1.setActive(true);
		ksession.insert(c1);
		
		Contract c2 = new Contract();
		c2.setCreditRequest(cr2);
		c2.setSigned(true);
		c2.setActive(true);
		ksession.insert(c2);
		
		Contract c3 = new Contract();
		c3.setCreditRequest(cr3);
		c3.setSigned(true);
		ksession.insert(c3);
		
		Contract c4 = new Contract();
		c4.setCreditRequest(cr4);
		c4.setSigned(true);
		c4.setActive(true);
		ksession.insert(c4);
		
		Contract c5 = new Contract();
		c5.setCreditRequest(cr5);
		c5.setSigned(true);
		ksession.insert(c5);
		
		Contract c6 = new Contract();
		c6.setCreditRequest(cr6);
		c6.setSigned(true);
		c6.setActive(true);
		ksession.insert(c6);
		
		ksession.fireAllRules();
	
		CreditCountDTO cc = null;
		for (Object obj : ksession.getObjects()) {
			if (obj instanceof CreditCountDTO) {
				cc = (CreditCountDTO) obj;
				break;
			}
		}
		
		assertThat(cc.getList().size(), equalTo(3));
		assertThat(cc.getList().get(0).getCount(), equalTo(3));
		assertThat(cc.getList().get(1).getCount(), equalTo(2));
		assertThat(cc.getList().get(2).getCount(), equalTo(1));

		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(ContractList.class));
		for (Object o : newEvents) {
			if (((ContractList) o).getMax()) {
				assertThat(((ContractList) o).getContracts().get(0).getCreditRequest().getSumOfMoney(), equalTo(90000.0));
			}
			else {
				assertThat(((ContractList) o).getContracts().get(0).getCreditRequest().getSumOfMoney(), equalTo(44000.0));
			}
		}
	}
}
