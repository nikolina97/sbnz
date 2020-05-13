package com.sbnz.bankcredit.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.bankcredit.model.User;

@Service
public class UserService {

	private final KieContainer kieContainer;
	
	@Autowired
	public UserService(KieContainer kieCont) {
		this.kieContainer = kieCont;
	}
	
	public void fireRules() { 
		User u = new User("admin", "123");
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.insert(u);
		kieSession.fireAllRules();
		
		System.out.println(u.getPassword());
	}
}
