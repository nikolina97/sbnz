package com.sbnz.bankcredit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.bankcredit.model.CreditRequest;

public interface ICreditRequestRepository extends JpaRepository<CreditRequest, Long> {

}
