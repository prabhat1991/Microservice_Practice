package com.prabhat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prabhat.clients.licenses.LicenseDiscoveryClient;
import com.prabhat.clients.licenses.LicenseFeignClient;
import com.prabhat.clients.licenses.LicenseRestTemplateClient;
import com.prabhat.config.AccessToken;
import com.prabhat.model.License;

@Service
public class LicenseSerivce {

	@Autowired
	private LicenseFeignClient licenseFeignClient;

	@Autowired
	private LicenseRestTemplateClient licenseRestClient;

	@Autowired
	private LicenseDiscoveryClient licenseDiscoveryClient;

	public List<License> loadLicenses(String clientType) {
		
		List<License> licenseList = null;

		switch (clientType) {
		case "feign":
			System.out.println("I am using the feign client");
			licenseList = licenseFeignClient.getAllLicenses(AccessToken.getAccessToken());
			break;
		case "rest":
			System.out.println("I am using the rest client");
			licenseList = licenseRestClient.loadLicenses();
			break;
		case "discovery":
			System.out.println("I am using the discovery client");
			licenseList = licenseDiscoveryClient.getAllLicenses();
			break;
		default:
			licenseList = licenseRestClient.loadLicenses();
		}
		return licenseList;
	}
}
