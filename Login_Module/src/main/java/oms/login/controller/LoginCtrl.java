package com.oms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oms.dto.CustomerDTO;
import com.oms.entity.JWTResponse;
import com.oms.service.LoginServ;

@RestController
@RequestMapping("/login")
public class LoginCtrl {
	
	@Autowired LoginServ loginServ;
	
	@PostMapping("/signIn")
	public ResponseEntity<JWTResponse> signIn(@RequestBody CustomerDTO cust){
		
        return new ResponseEntity(loginServ.signIn(cust), HttpStatus.OK);
	}
		
	@GetMapping("/admin")
	public ResponseEntity<JWTResponse> admin(@RequestParam String userName, @RequestParam String password){
		
		return new ResponseEntity(loginServ.signInAdmin(userName, password), HttpStatus.OK);
	}
	
}
