package com.sbnz.bankcredit.events;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.drools.core.ClassObjectFilter;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sbnz.bankcredit.model.Account;

public class AnnuityEventTest {
	
	@Test
    public void test() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        runPseudoClock(kContainer.newKieSession("kSessionPseudoClock"));
    }
	
	public void runPseudoClock(KieSession ksession) {
		
		String username = "user123";
		String accountNumber = "123-123-123";
		double loanInstallment = 300;
		double balance = 200;
		
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = null;
		try {
			date = formatter.parse("09/06/2020 12:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AnnuityEvent annuityEvent = new AnnuityEvent(loanInstallment, accountNumber, balance, username, date, false);
		Account account = new Account(200, accountNumber);
		ksession.insert(account);
		
		SessionPseudoClock clock = ksession.getSessionClock();
		ksession.insert(annuityEvent);
		clock.advanceTime(1, TimeUnit.DAYS);
	
		int count = ksession.fireAllRules();
		assertThat(count, equalTo(1));
		
        Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(ClientWarningEvent.class));
        assertThat(newEvents.size(), equalTo(1));
    	
	}
}
