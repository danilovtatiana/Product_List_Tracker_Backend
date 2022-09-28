package com.product.listtracker.service;

import java.time.LocalDateTime;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.product.listtracker.dao.AccountRepository;
import com.product.listtracker.dto.ProductDto;
import com.product.listtracker.dto.UserDto;
import com.product.listtracker.entities.Account;
import com.product.listtracker.entities.Product;
import com.product.listtracker.exceptions.AccountAlreadyExistsException;
import com.product.listtracker.exceptions.AccountDoesNotExistException;
import com.product.listtracker.exceptions.UsernameAlreadyExistsException;

@Service
public class AccountService {
	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Account registerAccount(Account account) throws Exception {
		final String email = account.getEmail();
		final String username = account.getUsername();
		
		//check if username is taken
		if (accountRepository.existsByUsername(username)) {
			throw new UsernameAlreadyExistsException(username);
		}
		
		//check if email is taken
		if (accountRepository.existsByEmail(email)) {
			throw new AccountAlreadyExistsException(email);
		}
		
		logger.info("Register account with email: {}", email);
		String rawPassword = account.getPassword();
		account.setPassword(passwordEncoder.encode(rawPassword));
		account.setCreationDate(LocalDateTime.now());
		return accountRepository.save(account);
	}
	
	public Account findByEmail(String email) throws AccountDoesNotExistException  {
		
		return accountRepository.findByEmail(email).orElseThrow();
	}
	
	public void deleteByEmail(String email) throws AccountDoesNotExistException  {
		Account account = accountRepository.findByEmail(email).orElseThrow();
		accountRepository.delete(account);
	}
	
	public UserDto updateUser(UserDto userDto) throws Exception  {
		
		Account updatedUser = accountRepository.findByEmail(userDto.getEmail()).orElseThrow();
		BeanUtils.copyProperties(userDto, updatedUser, "password", "accountId");
		
		//check if username is taken
		if (accountRepository.existsByUsername(updatedUser.getUsername())) {
			throw new UsernameAlreadyExistsException(updatedUser.getUsername());
		}
		
		updatedUser = accountRepository.save(updatedUser);
		
		userDto = new UserDto(updatedUser);
	
		return userDto;
	}
	
}

