package com.oms.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.oms.dto.CustomerDTO;
import com.oms.entity.Admin;
import com.oms.exception.UserNotFoundExcception;
import com.oms.service.LoginServ;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    RestTemplate restTemplate;
    
    @Override
    public UserDetails loadUserByUsername(String username){
		CustomerDTO userInfo = restTemplate.getForObject("http://localhost:9991/customer/getByEmail/"+username, CustomerDTO.class);
        if(userInfo == null) {
        	throw new UsernameNotFoundException("Username Not Found");
        }
        return new UserInfoUserDetails(userInfo);

    }
}

