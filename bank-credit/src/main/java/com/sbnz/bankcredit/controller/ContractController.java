package com.sbnz.bankcredit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.bankcredit.dto.ContractInfoDTO;
import com.sbnz.bankcredit.model.Contract;
import com.sbnz.bankcredit.service.ContractService;

@RestController
@RequestMapping(value = "/contracts", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContractController {
	
	@Autowired
	private ContractService contractService;
	
	@GetMapping("/all")
	public ResponseEntity<List<ContractInfoDTO>> getAllContracts() {
		return new ResponseEntity<>(contractService.getAllContracts(), HttpStatus.OK);
	}
	
	@PostMapping("/addContract")
	public ResponseEntity<?> addContract(@RequestBody Contract contract){
		return new ResponseEntity<>(contractService.addContract(contract), HttpStatus.OK);
	}

}
