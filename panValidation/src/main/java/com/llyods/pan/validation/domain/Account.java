package com.llyods.pan.validation.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Account {
	

	@NotBlank
	@Size(min=16, max=19, message="primaryAccountNumber should be between 16 to 19 characters")	
	private String primaryAccountNumber;
	private String status;
	
	public String getPrimaryAccountNumber() {
		return primaryAccountNumber;
	}
	public void setPrimaryAccountNumber(String primaryAccountNumber) {
		this.primaryAccountNumber = primaryAccountNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
