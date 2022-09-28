package com.product.listtracker.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.product.listtracker.entities.Account;

public class UserDto {
	
	private Long accountId;
	private String email;
	private String username;
	
	public UserDto() {
		
	}
	
	public UserDto(Account user){
		BeanUtils.copyProperties(user, this, "password");
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accoundId) {
		this.accountId = accoundId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
