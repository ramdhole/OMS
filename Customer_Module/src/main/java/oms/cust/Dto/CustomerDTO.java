package com.cg.mobileStore.Dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerDTO {

	private int customerId;
	private String customerName;
	
	@NotNull(message = "Please enter Password")
	private String password;
	
	@Pattern(regexp = "^\\d{10}$",  message = "Mobile number must be 10 digits")
	private String mobileNumber;
	
	private String emailId;

	private String address;
	
	private long cartId;
	
	private List<Long> orderId = new ArrayList<>();
	

}
