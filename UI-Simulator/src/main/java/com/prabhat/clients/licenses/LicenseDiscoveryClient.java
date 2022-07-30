package com.prabhat.clients.licenses;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.prabhat.config.AccessToken;
import com.prabhat.model.License;

@Component
public class LicenseDiscoveryClient {
	
	@Value("${zuulService.organizationserviceUrl}")
	private String organizationUrl;
	
	@Value("${zuulService.licensingserviceUrl}")
	private String licensingserviceUrl;

    @Autowired
    private DiscoveryClient discoveryClient;

	public List<License> getAllLicenses() {
		RestTemplate restTemplate = new RestTemplate();
		List<ServiceInstance> instances = discoveryClient.getInstances("licensingservice");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", AccessToken.getAccessToken());
		HttpEntity<License> licenseHttpEntity = new HttpEntity<License>(httpHeaders);
		if (instances.size() == 0)
			return null;
		
		String serviceUri = String.format("%s/v1/licenses/", instances.get(0).getUri().toString());
		ResponseEntity<List> responseEntity = restTemplate.exchange(serviceUri, HttpMethod.GET, licenseHttpEntity, List.class);
		return responseEntity.getBody();
	}
	
	public void saveLicense(License license) {
		RestTemplate restTemplate = new RestTemplate();
		List<ServiceInstance> instances = discoveryClient.getInstances("licensingservice");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", AccessToken.getAccessToken());
		HttpEntity<License> licenseHttpEntity = new HttpEntity<License>(license, httpHeaders);
		
		if (instances.size() == 0)
			return;
		
		String serviceUri = String.format("%s/v1/licenses/", instances.get(0).getUri().toString());
		restTemplate.exchange(serviceUri, HttpMethod.POST, licenseHttpEntity, Void.class);
	}
}
