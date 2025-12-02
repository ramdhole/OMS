package com.oms.service;

import com.oms.dto.CustomerDTO;
import com.oms.entity.JWTResponse;

public interface LoginServ {

	public CustomerDTO getByUserName(String userName);
	
	public JWTResponse signIn(CustomerDTO cust);

	public JWTResponse signInAdmin(String userName, String pass); 

}
