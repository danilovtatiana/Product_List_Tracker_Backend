package com.product.listtracker.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.listtracker.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	Optional<Account> findByEmail(String email);
	void deleteByEmail(String email);
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);
}