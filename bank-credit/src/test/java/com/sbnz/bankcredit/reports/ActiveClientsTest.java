package com.sbnz.bankcredit.reports;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.drools.core.ClassObjectFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sbnz.bankcredit.dto.ActiveClientsDTO;
import com.sbnz.bankcredit.dto.ContractList;
import com.sbnz.bankcredit.dto.CreditCountDTO;
import com.sbnz.bankcredit.model.Client;
import com.sbnz.bankcredit.model.Contract;
import com.sbnz.bankcredit.model.CreditRequest;
import com.sbnz.bankcredit.model.CreditType;

public class ActiveClientsTest {
	
	@Test
    public void test() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kieSession = kContainer.newKieSession("reportsSession");
		run(kieSession);
    }
	
	public void run(KieSession ksession) {
		
		Client client1 = new Client();
		client1.setJmbg("123-123-123");
		client1.setRewardPoints(20);
		
		Client client2 = new Client();
		client2.setRewardPoints(10);
		client2.setJmbg("123-123-122");
		
		Client client3 = new Client();
		client3.setRewardPoints(15);
		client3.setJmbg("123-123-124");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date sDate1 = null;
		Date sDate2 = null;
		Date sDate3 = null;
		Date sDate4 = null;
		Date sDate5 = null;
		try {
			sDate1 = formatter.parse("09/06/2018 12:00:00");
			sDate2 = formatter.parse("09/06/2019 12:00:00");
			sDate3 = formatter.parse("03/01/2020 12:00:00");
			sDate4 = formatter.parse("09/03/2014 12:00:00");
			sDate5 = formatter.parse("19/04/2017 12:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Contract contract1 = new Contract();
		Contract contract2 = new Contract();
		Contract contract3 = new Contract();
		Contract contract4 = new Contract();
		Contract contract5 = new Contract();
		
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
		
		contract1.setSigned(true);
		contract1.setSigningDate(new Timestamp(sDate1.getTime()));
		contract1.setClient(client1);
		contract1.setCreditRequest(cr1);
		
		contract2.setSigned(true);
		contract2.setSigningDate(new Timestamp(sDate2.getTime()));
		contract2.setClient(client3);
		contract2.setCreditRequest(cr2);
		
		contract3.setSigned(true);
		contract3.setSigningDate(new Timestamp(sDate3.getTime()));
		contract3.setClient(client3);
		contract3.setCreditRequest(cr3);
		
		contract4.setSigned(true);
		contract4.setSigningDate(new Timestamp(sDate4.getTime()));
		contract4.setClient(client1);
		contract4.setCreditRequest(cr4);
		
		contract5.setSigned(true);
		contract5.setSigningDate(new Timestamp(sDate5.getTime()));
		contract5.setClient(client1);
		contract5.setCreditRequest(cr5);
		
		ksession.insert(contract1);
		ksession.insert(contract2);
		ksession.insert(contract3);
		ksession.insert(contract4);
		ksession.insert(contract5);
		
		ksession.fireAllRules();
		
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(ActiveClientsDTO.class));
		for (Object o : newEvents) {
			assertThat(((ActiveClientsDTO) o).getClients().size(), equalTo(2));
		}
	}
}
