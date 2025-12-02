package com.oms.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerDTO {

	private int customerId;
	private String customerName;
	
	private String password;
	@Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
	private String mobileNumber;

	private String emailId;

	private String address;

	private long cartId;

	private List<Long> orderId = new ArrayList<>();
}
