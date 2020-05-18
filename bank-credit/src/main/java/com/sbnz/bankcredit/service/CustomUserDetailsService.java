package com.sbnz.bankcredit.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sbnz.bankcredit.model.Client;
import com.sbnz.bankcredit.model.User;
import com.sbnz.bankcredit.repository.IAuthorityRepository;
import com.sbnz.bankcredit.repository.IClientRepository;
import com.sbnz.bankcredit.repository.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	protected final Log LOGGER = LogFactory.getLog(getClass());
	
	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IAuthorityRepository authorityRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IClientRepository clientRepository;

	// Funkcija koja na osnovu username-a iz baze vraca objekat User-a
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails u =  userRepository.findByUsername(username);
		if(u!= null)
			return u;
		else
			throw new UsernameNotFoundException(String.format("User with username '%s' not found", username));
	}
	
	public Client getClient(User user) {
		Client client = clientRepository.findById(user.getId()).orElse(null);
		return client;
	}

}
