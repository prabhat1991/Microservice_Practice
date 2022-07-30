package com.prabhat.licenses.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.prabhat.licenses.filter.AccessToken;
import com.prabhat.licenses.model.Organization;

@Component
public class OrganizationRestTemplateClient {
    @Autowired
    RestTemplate restTemplate;
    

    public Organization getOrganization(String organizationId){
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Authorization", AccessToken.getAccessToken());
    	
    	HttpEntity<Organization> httpEntity = new HttpEntity<Organization>(headers);
        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        "http://organizationservice/v1/organizations/{organizationId}",
                        HttpMethod.GET,
                        httpEntity, Organization.class, organizationId);

        return restExchange.getBody();
    }
}
