package com.sbnz.bankcredit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.bankcredit.dto.CreditRequestDTO;
import com.sbnz.bankcredit.dto.RealEstateDTO;
import com.sbnz.bankcredit.model.Answer;
import com.sbnz.bankcredit.model.Client;
import com.sbnz.bankcredit.model.Contract;
import com.sbnz.bankcredit.model.CreditRequest;
import com.sbnz.bankcredit.model.RealEstate;
import com.sbnz.bankcredit.model.User;
import com.sbnz.bankcredit.service.CreditRequestService;
import com.sbnz.bankcredit.service.CustomUserDetailsService;

@RestController
@RequestMapping(value = "/credit-request", produces = MediaType.APPLICATION_JSON_VALUE)
public class CreditRequestController {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private CreditRequestService creditRequestService;
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping( value = "/basic")
	public ResponseEntity<Answer> basicRulesInRequest(@RequestBody CreditRequestDTO creditReqDTO) {
		Answer answer = creditRequestService.checkBasicRules(creditReqDTO);
		System.out.println(answer);
		return new ResponseEntity<Answer>(answer, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping(value = "/warrantly-fulfillment")
	public ResponseEntity<?> warrantlyFulfillment(@RequestBody CreditRequest cr) {
	
		Answer answer = creditRequestService.checkwarrantlyFulfillment(cr);
		System.out.println(answer);
		return new ResponseEntity<>(answer, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PostMapping( value = "/create-contract")
	public ResponseEntity<?> basicClientRules(@RequestBody Contract cr) {
		Answer answer = creditRequestService.createContract(cr);
		return new ResponseEntity<>(answer, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping( value = "/proba")
    public ResponseEntity<?> proba() {
		
		User uclient = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Client client = userDetailsService.getClient(uclient);
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	
}
