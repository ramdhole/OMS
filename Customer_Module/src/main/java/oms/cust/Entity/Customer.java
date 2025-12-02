package com.cg.mobileStore.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name="customer")
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	private String customerName;
	
	@NotNull(message = "Please enter Password")
	private String password;
	
	@Column(unique = true)
	@Pattern(regexp = "^\\d{10}$",  message = "Mobile number must be 10 digits")
	private String mobileNumber;
	
	@Column(unique = true)
	private String emailId;

	private String address;
	
	@Column(unique = true)
	private long cartId;
	
	private List<Long> orderId = new ArrayList<>();

}
