package com.oms.dto;

import com.oms.entity.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {
	private int customerId;
	private String customerName;
	private String mobileNumber;
	private String emailId;
	private String address;
	private Cart cart;
}
