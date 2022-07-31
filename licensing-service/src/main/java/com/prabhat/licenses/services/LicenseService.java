package com.prabhat.licenses.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prabhat.licenses.clients.OrganizationDiscoveryClient;
import com.prabhat.licenses.clients.OrganizationFeignClient;
import com.prabhat.licenses.clients.OrganizationRestTemplateClient;
import com.prabhat.licenses.config.ServiceConfig;
import com.prabhat.licenses.model.License;
import com.prabhat.licenses.model.Organization;
import com.prabhat.licenses.repository.LicenseRepository;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    ServiceConfig config;

    @Autowired
    OrganizationFeignClient organizationFeignClient;

    @Autowired
    OrganizationRestTemplateClient organizationRestClient;

    @Autowired
    OrganizationDiscoveryClient organizationDiscoveryClient;


    private Organization retrieveOrgInfo(String organizationId, String clientType){
        Organization organization = null;

        switch (clientType) {
            case "feign":
                System.out.println("I am using the feign client");
                organization = organizationFeignClient.getOrganization(organizationId);
                break;
            case "rest":
                System.out.println("I am using the rest client");
                organization = organizationRestClient.getOrganization(organizationId);
                break;
            case "discovery":
                System.out.println("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            default:
                organization = organizationRestClient.getOrganization(organizationId);
        }

        return organization;
    }

    public License getLicense(String organizationId,String licenseId, String clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        Organization org = retrieveOrgInfo(organizationId, clientType);

        return license.withOrganizationName(org.getName())
		            .withContactName(org.getContactName())
		            .withContactEmail(org.getContactEmail())
		            .withContactPhone(org.getContactPhone());
    }

    public List<License> getLicensesByOrg(String organizationId){
        return licenseRepository.findByOrganizationId( organizationId );
    }

    public List<License> getAllLicenses(){
    	List<License> licenseList= StreamSupport.stream(licenseRepository.findAll().spliterator(), false).collect(Collectors.toList());
    	for(License license : licenseList) {
    		Organization org = retrieveOrgInfo(license.getOrganizationId(), "rest");
    		if(org!= null) {
	    		license.withOrganizationName(org.getName())
			            .withContactName(org.getContactName())
			            .withContactEmail(org.getContactEmail())
			            .withContactPhone(org.getContactPhone());
    		}
    	}
    	
    	return licenseList;
    }

    
    public void saveLicense(License license){
        license.withId( UUID.randomUUID().toString());
        licenseRepository.save(license);

    }

    public void updateLicense(License license){
      licenseRepository.save(license);
    }

    public void deleteLicense(License license){
        licenseRepository.delete( license.getLicenseId());
    }

}
