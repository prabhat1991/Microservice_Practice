package com.prabhat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.prabhat.config.AccessToken;
import com.prabhat.model.License;
import com.prabhat.service.LicenseSerivce;

@Controller
public class SampleController {
	
	@Autowired
    RestTemplate restTemplate;

	@Autowired
	private LicenseSerivce licenseSerivce;
	
	@RequestMapping(value = "/")
	public String loadUI() {
		return "home";
	}

	@RequestMapping(value = "/secure")
	public String loadSecuredUI() {
		return "secure";
	}
	
	@RequestMapping(value = "/addlicense")
	public String loadAddlicenseUI(Model model) {
		License license = new License();
		model.addAttribute("license", license);
		return "addlicense";
	}
	
	@RequestMapping(value = "/license", method = RequestMethod.POST)
	public String addlicense(@ModelAttribute("license") License license, Model model) {
		try {
			licenseSerivce.saveLicense(license, "rest");
			model.addAttribute("licenseAdded", true);
		} catch (HttpStatusCodeException e) {
			ResponseEntity responseEntity = ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders()).body(e.getResponseBodyAsString());
			model.addAttribute("error", responseEntity);
		} catch (Exception e) {
			ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
			model.addAttribute("error", responseEntity);
		}
		return "secure";
	}
	
	@RequestMapping(value = "/license", method = RequestMethod.GET)
	public String loadLicenses(Model model) {

		try {
			List<License> licenses = licenseSerivce.loadLicenses("rest");
			model.addAttribute("licenses", licenses);
		} catch (HttpStatusCodeException e) {
			ResponseEntity responseEntity = ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders()).body(e.getResponseBodyAsString());
			model.addAttribute("error", responseEntity);
		} catch (Exception e) {
			ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
			model.addAttribute("error", responseEntity);
		}
		return "secure";
	}
}
