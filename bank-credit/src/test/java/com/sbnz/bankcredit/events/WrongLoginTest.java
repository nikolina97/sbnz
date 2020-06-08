package com.sbnz.bankcredit.events;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.drools.core.time.SessionPseudoClock;
import org.junit.Test; 
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


public class WrongLoginTest {

	@Test
    public void test() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        runPseudoClock(kContainer.newKieSession("kSessionPseudoClock"));
    }
	
	public void runPseudoClock(KieSession ksession) {
		String username = "marko";
		
		SessionPseudoClock clock = ksession.getSessionClock();
		LoginEvent loginEvent1 = new LoginEvent(username, false);
		LoginEvent loginEvent2 = new LoginEvent(username, false);
		LoginEvent loginEvent3 = new LoginEvent(username, false);
		
		ksession.insert(loginEvent1);
		clock.advanceTime(10, TimeUnit.SECONDS);
		
		ksession.insert(loginEvent2);
		clock.advanceTime(10, TimeUnit.SECONDS);
		int count = ksession.fireAllRules();
		assertThat(count, equalTo(0));
		
		ksession.insert(loginEvent3);
		clock.advanceTime(10, TimeUnit.SECONDS);
		
		count = ksession.fireAllRules();
		assertThat(count, equalTo(1));
		
		
	}
	
	
}
