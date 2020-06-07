package com.sbnz.bankcredit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.bankcredit.model.Account;

public interface IAccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findOneByAccountNumber(String accountNumber);
}
