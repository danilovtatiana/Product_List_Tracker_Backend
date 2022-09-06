package com.product.listtracker.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ACCOUNT_ID")
	private Long accountId;
	
	@NotBlank (message = "Email cannot be blank!")
	@Column (name = "EMAIL", length = 30, nullable = false, unique = true)
	private String email;
	
	@NotBlank (message = "Username cannot be blank!")
	@Column (name = "USERNAME", length = 50, nullable = false)
	@Size (min = 5, max = 50)
	private String userName;
	
	@NotBlank (message = "Password cannot be blank!")
	@Column (name = "PASSWORD", nullable = false)
	private String password;
	
	@Column (name = "CREATION_DATE", nullable = false)
	private LocalDateTime creationDate;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", email=" + email + ", userName=" + userName + ", password="
				+ password + ", creationDate=" + creationDate + "]";
	}
	
	

}
