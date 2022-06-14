package com.prabhat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.prabhat.config.AccessToken;
import com.prabhat.model.License;

@Controller
public class SampleController {
	
	@Autowired
    RestTemplate restTemplate;

	@RequestMapping(value = "/")
	public String loadUI() {
		return "home";
	}

	@RequestMapping(value = "/secure")
	public String loadSecuredUI() {
		return "secure";
	}
	
	@RequestMapping(value = "/customers")
    public String loadCustomers(Model model) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessToken.getAccessToken());
        HttpEntity<License> licenseHttpEntity = new HttpEntity<License>(httpHeaders);
        try {
            ResponseEntity<License[]> responseEntity = restTemplate.exchange("http://localhost:8081/v1/organizations/e254f8c-c442-4ebe-a82a-e2fc1d1ff78a/licenses/", HttpMethod.GET, licenseHttpEntity, License[].class);
            model.addAttribute("licenses", responseEntity.getBody());
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