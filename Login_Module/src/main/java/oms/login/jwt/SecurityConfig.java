package com.oms.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	 @Autowired
	    private JWTAuthenticationEntryPoint point;
	    @Autowired
	    private JwtAuthenticationFilter filter;

	    @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    
	    	http.csrf(csrf -> csrf.disable())
	    		.cors(cors -> cors.disable())
	    		.authorizeHttpRequests(
	    				auth -> auth.requestMatchers("/home/**").authenticated()
	    					.requestMatchers("/login/signIn").permitAll()
	    					.anyRequest().authenticated())
	    		.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
	    		.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	    	
	    	http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	    	return http.build();
	    }
}
