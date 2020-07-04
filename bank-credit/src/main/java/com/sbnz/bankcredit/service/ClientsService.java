package com.sbnz.bankcredit.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sbnz.bankcredit.dto.ClientDTO;
import com.sbnz.bankcredit.model.Account;
import com.sbnz.bankcredit.model.Authority;
import com.sbnz.bankcredit.model.Client;
import com.sbnz.bankcredit.model.User;
import com.sbnz.bankcredit.model.UserType;
import com.sbnz.bankcredit.repository.IAccountRepository;
import com.sbnz.bankcredit.repository.IAuthorityRepository;
import com.sbnz.bankcredit.repository.IClientRepository;
import com.sbnz.bankcredit.repository.IUserRepository;

@Service
public class ClientsService {

	@Autowired
	private IClientRepository clientRepository;
	
	@Autowired
	private IAccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IAuthorityRepository authorityRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	public void encodePassword(User u) {
		String pass =  this.passwordEncoder.encode(u.getPassword());
		u.setPassword(pass);
	}
	
	public Authority findAuthority(Long id) {
		return authorityRepository.findById(id).get();
	}
	
	public List<Client> getAllClients() {
		List<Client> clients = clientRepository.findByActive(true);
		return clients;
	}

	public Client editClient(ClientDTO client) throws InvalidDataException {
		
		if(Stream.of(client.getAccount(), client.getMonthlyIncome(), client.getMonthlyOutcome(), client.getRewardPoints()).anyMatch(Objects::isNull))
			throw new InvalidDataException("Neki podaci nedostaju");
		
		if (client.getMonthlyIncome() < 0 || client.getMonthlyOutcome() < 0 || client.getRewardPoints() <0) {
			throw new InvalidDataException("Neispravni podaci");
		}
		
		Optional<Client> cl = clientRepository.findById(client.getClientId());
		if (!cl.isPresent()) { 
			throw new InvalidDataException("Neispravni podaci");
		}
		
		Client clien_ = cl.get();
		clien_.setAccount(client.getAccount());
		clien_.setMonthlyIncome(client.getMonthlyIncome());
		clien_.setMonthlyOutcome(client.getMonthlyOutcome());
		clien_.setRewardPoints(client.getRewardPoints());
		
		clien_ = clientRepository.save(clien_);
		return clien_;
	}

	public Client addClient(Client client) throws InvalidDataException {
		System.out.println(client.getFirstName());
		Date now = new Date();
		client.setAccountOpeningDate(new Timestamp(now.getTime()));
		if(Stream.of(client.getJmbg(),client.getFirstName(),client.getDateOfBirth(), client.getLastName(), client.getPassword(), client.getId(),
				client.getAccount(), client.getMonthlyIncome(), client.getMonthlyOutcome()).anyMatch(Objects::isNull)) {
			throw new InvalidDataException("Neki podaci nedostaju");
		}
		
		Optional<Account> accExists = accountRepository.findOneByAccountNumber(client.getAccount().getAccountNumber());
		
		if (accExists.isPresent()) {
			throw new InvalidDataException("Neispravan broj računa");
		}
		
		Optional<Client> jmbgExists = clientRepository.findOneByJmbg(client.getJmbg());
		
		if (jmbgExists.isPresent()) {
			throw new InvalidDataException("Neispravan jmbg");
		}
		
		encodePassword(client);
		encodePassword(client);
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = findAuthority((long)2);
		a.setUserType(UserType.ROLE_CLIENT);
		authorities.add(a);
		client.setAuthorities(authorities);
		this.accountRepository.save(client.getAccount());
		this.userRepository.save(client);
		return client;
	}

	public Client remove(Client client) {
		Client c = clientRepository.findById(client.getId()).orElse(null);
		c.setActive(false);
		this.userRepository.save(c);
		return c;
	}

	public Account getAccount() {
		User uclient = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Client client = userDetailsService.getClient(uclient);
		return client.getAccount();
	}

}
