package com.sbnz.bankcredit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.bankcredit.dto.ActiveClientsDTO;
import com.sbnz.bankcredit.dto.ContractList;
import com.sbnz.bankcredit.dto.CreditCountDTO;
import com.sbnz.bankcredit.service.ReportsService;

@RestController
@RequestMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportsController {
	
	@Autowired
	private ReportsService reportsService;
	
	@GetMapping("/countByType")
	public ResponseEntity<CreditCountDTO> getCreditsCountGroupedByType() {
		return new ResponseEntity<CreditCountDTO>(reportsService.getCountOfCreditsByType(), HttpStatus.OK);
	}
	
	@GetMapping("/largestLoan")
	public ResponseEntity<ContractList> getLargestLoan() {
		return new ResponseEntity<ContractList>(reportsService.getLargestLoan(), HttpStatus.OK);
	}
	
	@GetMapping("/smallestLoan")
	public ResponseEntity<ContractList> getSmallestLoan() {
		return new ResponseEntity<ContractList>(reportsService.getSmallestLoan(), HttpStatus.OK);
	}
	
	@GetMapping("/mostActiveClients")
	public ResponseEntity<ActiveClientsDTO> getMostActiveClients() {
		return new ResponseEntity<ActiveClientsDTO>(reportsService.getMostActiveClients(), HttpStatus.OK);
	}
	
}
