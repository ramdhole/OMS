package com.oms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.oms.dto.CustomerDTO;
import com.oms.entity.JWTResponse;
import com.oms.exception.UserNotFoundExcception;
import com.oms.jwt.JwtHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServImpl implements LoginServ{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
    private JwtHelper helper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String url ="http://localhost:9991/customer"; 

	@Override
	public CustomerDTO getByUserName(String userName) {
		CustomerDTO cust = restTemplate.getForObject(url+"/getByEmail/"+userName, CustomerDTO.class);
		if(cust == null)
			throw new UserNotFoundExcception("Username not found!");
		
		log.info("User Found");
		return cust;
	}

	@Override
	public JWTResponse signIn(CustomerDTO cust) {
		
		this.doAuthenticate(cust.getEmailId(), cust.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(cust.getEmailId());
        String token = this.helper.generateToken(userDetails);

        JWTResponse response = JWTResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        
        log.info("User Succesfully Logged In");
        return response;
	}
	
	private void doAuthenticate(String email, String password) {
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authentication);
			
			
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
	}

	@Override
	public JWTResponse signInAdmin(String userName, String pass) {
		if(userName != "admin@123" && pass != "12345") 
			throw new UsernameNotFoundException("Admin Not Found !!");
		
		
		this.doAuthenticate("ram@123", "$10$NYPtTYaFalEoKQRxvoz4Te93R5k.L3/yKEWxhtlWVPvJp0hM5vQKK");
		
		UserDetails userDetails = userDetailsService.loadUserByUsername("ram@123");
		String token = this.helper.generateToken(userDetails);
		
		JWTResponse response = JWTResponse.builder()
				.jwtToken(token)
				.username(userDetails.getUsername()).build();
		
		log.info("Successfully Logged in as Admin !!");
		return response;
	}
	
	

}
