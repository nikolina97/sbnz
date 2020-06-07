package com.sbnz.bankcredit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.bankcredit.model.Client;

public interface IClientRepository extends JpaRepository<Client, Long> {
	 
	Optional<Client> findOneByJmbg(String jmbg);
	
}
