package com.prabhat.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.prabhat.config.ServiceConfig;
import com.prabhat.model.License;
import com.prabhat.security.AccessToken;

@Component
public class LicenseRestTemplateClient {
	
	@Autowired
	private ServiceConfig serviceConfig;
	
    @Autowired
    RestTemplate restTemplate;
    

    public List<License> loadLicenses(){
    	HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", AccessToken.getAccessToken());
		HttpEntity<License> licenseHttpEntity = new HttpEntity<License>(httpHeaders);
		ResponseEntity<List> responseEntity = restTemplate.exchange(serviceConfig.getZuulLicensingserviceUrl().concat("/v1/licenses/"), HttpMethod.GET, licenseHttpEntity, List.class);
        return responseEntity.getBody();
    	
    }
    
    public void saveLicense(License license){
    	HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", AccessToken.getAccessToken());
		HttpEntity<License> licenseHttpEntity = new HttpEntity<License>(license, httpHeaders);
		String uri = String.format(serviceConfig.getZuulLicensingserviceUrl().concat("/v1/licenses/"), license.getOrganizationId());
		restTemplate.exchange(uri, HttpMethod.POST, licenseHttpEntity, Void.class);
    	
    }
}
