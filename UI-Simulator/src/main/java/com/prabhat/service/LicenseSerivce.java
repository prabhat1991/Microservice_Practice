package com.prabhat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prabhat.clients.LicenseDiscoveryClient;
import com.prabhat.clients.LicenseFeignClient;
import com.prabhat.clients.LicenseRestTemplateClient;
import com.prabhat.model.License;
import com.prabhat.security.AccessToken;

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

	public void saveLicense(License license, String clientType) {

		switch (clientType) {
		case "feign":
			System.out.println("I am using the feign client");
			licenseFeignClient.saveLicenses(license, AccessToken.getAccessToken());
			break;
		case "rest":
			System.out.println("I am using the rest client");
			licenseRestClient.saveLicense(license);
			break;
		case "discovery":
			System.out.println("I am using the discovery client");
			licenseDiscoveryClient.saveLicense(license);
			break;
		default:
			licenseRestClient.saveLicense(license);
		}
	}
}
