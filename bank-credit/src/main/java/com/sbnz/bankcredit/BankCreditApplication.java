package com.sbnz.bankcredit;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sbnz.bankcredit.model.RealEstate;
import com.sbnz.bankcredit.model.RealEstateType;

@SpringBootApplication
public class BankCreditApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BankCreditApplication.class, args);

	}
	
//	@Bean
//	public KieContainer kieContainer() {
//		return KieServices.Factory.get().getKieClasspathContainer();
//	}
	
}
