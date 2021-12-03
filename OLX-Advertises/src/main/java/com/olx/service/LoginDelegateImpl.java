package com.olx.service;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class LoginDelegateImpl implements LoginDelegate{
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	@CircuitBreaker(name = "TOKEN-VALIDATION-FROM-LOGIN-SERVICE",fallbackMethod = "fallBackTokenValidation")
	public Boolean isValidToken(String token) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<Boolean> result = this.restTemplate.exchange("http://API-GATEWAY/olx/user/validate/token",
											HttpMethod.GET ,entity,Boolean.class);
		return result.getBody();
		
		
	}
	
	public Boolean fallBackTokenValidation(String token,Exception ex) {
		System.out.println("Fall Back Method Called "+ex);
		return false;
	}

}
