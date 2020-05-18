package com.sbnz.bankcredit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.bankcredit.model.Authority;

public interface IAuthorityRepository extends JpaRepository<Authority, Long>{

}
