package com.product.listtracker.service;

import java.time.LocalDateTime;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.product.listtracker.dao.AccountRepository;
import com.product.listtracker.entities.Account;
import com.product.listtracker.exceptions.AccountAlreadyExistsException;

@Service
public class AccountService {
	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Account registerAccount(Account account) throws AccountAlreadyExistsException {
		final String email = account.getEmail();
		if (accountRepository.existsByEmail(email)) {
			throw new AccountAlreadyExistsException(email);
		}
		logger.info("Register account with email: {}", email);
		String rawPassword = account.getPassword();
		account.setPassword(passwordEncoder.encode(rawPassword));
		account.setCreationDate(LocalDateTime.now());
		return accountRepository.save(account);
	}
	
}

