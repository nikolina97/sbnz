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

public class SuspiciousUserEventTest {
	
	@Test
    public void test() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        runPseudoClock(kContainer.newKieSession("kSessionPseudoClock"));
    }
	
	public void runPseudoClock(KieSession ksession) {
		
		String username = "user123";
		String accountNumber = "123-123-123";
		String reason = "Klijent nema dovoljnu sumu novca u dogovorenom terminu";
		
		ClientWarningEvent clientWarningEvent1 = new ClientWarningEvent(username, reason);
		ClientWarningEvent clientWarningEvent2 = new ClientWarningEvent(username, reason);
		ClientWarningEvent clientWarningEvent3 = new ClientWarningEvent(username, reason);
		ksession.insert(clientWarningEvent1);
		SessionPseudoClock clock = ksession.getSessionClock();
		clock.advanceTime(60, TimeUnit.DAYS);
		ksession.insert(clientWarningEvent2);
		int count = ksession.fireAllRules();
		Collection<?> newEvents = ksession.getObjects(new ClassObjectFilter(SuspiciousUserEvent.class));
		assertThat(newEvents.size(), equalTo(0));
		
		clock.advanceTime(30, TimeUnit.DAYS);
		ksession.insert(clientWarningEvent3);
		
		RaisingMoneyEvent rm1 = new RaisingMoneyEvent(username, accountNumber, 600);
		RaisingMoneyEvent rm2 = new RaisingMoneyEvent(username, accountNumber, 550);
		RaisingMoneyEvent rm3 = new RaisingMoneyEvent(username, accountNumber, 900);

		ksession.insert(rm1);
		clock.advanceTime(10, TimeUnit.MINUTES);
		ksession.insert(rm2);
		clock.advanceTime(20, TimeUnit.MINUTES);
		ksession.insert(rm3);
		count = ksession.fireAllRules();
		newEvents = ksession.getObjects(new ClassObjectFilter(SuspiciousUserEvent.class));
		assertThat(newEvents.size(), equalTo(2));
			
		}
}
