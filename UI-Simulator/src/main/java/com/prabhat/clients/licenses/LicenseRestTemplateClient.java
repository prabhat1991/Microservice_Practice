package com.prabhat.clients.licenses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.prabhat.config.AccessToken;
import com.prabhat.model.License;

@Component
public class LicenseRestTemplateClient {
	
    @Autowired
    RestTemplate restTemplate;
    

    public List<License> loadLicenses(){
    	HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", AccessToken.getAccessToken());
		HttpEntity<License> licenseHttpEntity = new HttpEntity<License>(httpHeaders);
    	
		ResponseEntity<List> responseEntity = restTemplate.exchange("http://LICENSINGSERVICE/v1/licenses/",
				HttpMethod.GET, licenseHttpEntity, List.class);
    	
        return responseEntity.getBody();
    	
    }
}
