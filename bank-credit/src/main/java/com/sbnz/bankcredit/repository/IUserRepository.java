package com.sbnz.bankcredit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.bankcredit.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
