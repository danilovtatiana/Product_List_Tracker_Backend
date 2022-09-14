package com.product.listtracker.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.product.listtracker.entities.Account;

public class UserDto {
	
	private Long accoundId;
	private String email;
	private String username;
	private LocalDateTime creationDate; 
	
	public UserDto() {
		
	}
	
	public UserDto(Account user){
		BeanUtils.copyProperties(user, this, "password");
	}

	public Long getAccoundId() {
		return accoundId;
	}

	public void setAccoundId(Long accoundId) {
		this.accoundId = accoundId;
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

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	

}
