package com.oms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JWTResponse {

	private String jwtToken;
	
	private String username;
	
	public JWTResponse(String token) {
		this.jwtToken = token;
	}
}
