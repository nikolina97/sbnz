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

import com.sbnz.bankcredit.dto.ClientDTO;
import com.sbnz.bankcredit.model.Client;
import com.sbnz.bankcredit.service.ClientsService;
import com.sbnz.bankcredit.service.InvalidDataException;

@RestController
@RequestMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientsController {
	
	@Autowired
	private ClientsService clientsService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Client>> getAllClients() {
		return new ResponseEntity<>(clientsService.getAllClients(), HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> editClient(@RequestBody ClientDTO client) {
		try {
			return new ResponseEntity<>(clientsService.editClient(client), HttpStatus.OK);
		} catch (InvalidDataException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/addNew")
	public ResponseEntity<?> addNewClient(@RequestBody Client client) {
		try {
			return new ResponseEntity<>(clientsService.addClient(client), HttpStatus.OK);
		} catch (InvalidDataException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/remove")
	public ResponseEntity<?> removeClient(@RequestBody Client client) {
		try {
			return new ResponseEntity<>(clientsService.remove(client), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}
