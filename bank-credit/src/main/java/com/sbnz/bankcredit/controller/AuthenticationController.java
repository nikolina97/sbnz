package com.sbnz.bankcredit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.bankcredit.service.UserService;


@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
	
	@Autowired
	private UserService uService;
	
	@GetMapping( value = "/proba")
    public ResponseEntity<String> logoutUser() {
		
			uService.fireRules();
            return new ResponseEntity<>("You successfully logged in!", HttpStatus.OK);

    }

}
