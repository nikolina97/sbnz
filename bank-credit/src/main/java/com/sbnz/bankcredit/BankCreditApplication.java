package com.sbnz.bankcredit;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
