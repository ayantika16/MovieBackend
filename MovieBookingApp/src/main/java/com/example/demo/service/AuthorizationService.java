package com.example.demo.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class AuthorizationService {

	public Boolean validateJwt(String jwt) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url="http://localhost:9090/auth/v1/validate";
		
		String newjwt="Bearer "+jwt;
		
		System.out.println(jwt);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", jwt);
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

	
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<Boolean> response = restTemplate.postForEntity(url, entity, Boolean.class);

		return response.getBody();
	}
}
