package com.cafe24.bitapp.frontend.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BitappRestTemplate {
	public static final String BACKENDHOST = "https://src88222.cafe24api.com";
	
	
	public static String request(String uri, HttpMethod method ,String params, String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        // 인증
        if(accessToken != null) headers.add("Authorization", "Bearer " + accessToken);
        headers.add("X-Cafe24-Api-Version", "");
        headers.add("Content-Type", "application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> body = new HttpEntity<String>(params, headers);
        
    	try {
    		ResponseEntity<String> response = restTemplate.exchange(new URI(BACKENDHOST + uri), method, body, String.class);
    		String result = response.getBody();
        	
    		return result;
		} catch (HttpClientErrorException e) {
			return e.getMessage();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
        return null;
    }
}
