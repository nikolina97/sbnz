package com.sbnz.bankcredit.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.bankcredit.model.Client;
import com.sbnz.bankcredit.model.Contract;


public interface IContractRepository extends JpaRepository<Contract, Long>{
	
	Collection<Contract> findAllByClient(Client client);
	Collection<Contract> findAllBySigned (Boolean signed);
}
