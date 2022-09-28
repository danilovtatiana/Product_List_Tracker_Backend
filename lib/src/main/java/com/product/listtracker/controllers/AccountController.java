package com.product.listtracker.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.listtracker.dto.ProductDto;
import com.product.listtracker.dto.UserDto;
import com.product.listtracker.entities.Account;
import com.product.listtracker.entities.Product;
import com.product.listtracker.exceptions.AccountAlreadyExistsException;
import com.product.listtracker.exceptions.AccountDoesNotExistException;
import com.product.listtracker.service.AccountService;



@RestController
@RequestMapping("/account")
public class AccountController {
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
	
	
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account registerAccount(@Valid @RequestBody Account account) throws Exception {
		logger.info("[REST] POST to /account/register");
		return accountService.registerAccount(account);
	}
	
	@GetMapping(value = "/me")
	public ResponseEntity <UserDto> getUser() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		
		Account account;
		try {
			account = accountService.findByEmail(userEmail);
			
			return new ResponseEntity<>(new UserDto(account), HttpStatus.OK);
		} catch (AccountDoesNotExistException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new UserDto(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = "/me")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) throws Exception {
		try {
			UserDto updateUser = accountService.updateUser(userDto);
			return new ResponseEntity<>(updateUser, HttpStatus.OK);
		} catch (AccountDoesNotExistException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new UserDto(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/me")
	public ResponseEntity<Void> delete() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		
		try {
			accountService.deleteByEmail(userEmail);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (AccountDoesNotExistException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

}


