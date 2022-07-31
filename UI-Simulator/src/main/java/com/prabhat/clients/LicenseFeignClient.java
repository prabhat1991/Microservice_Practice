package com.prabhat.clients;


import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prabhat.model.License;

@FeignClient("licensingservice")
public interface LicenseFeignClient {
	
    @RequestMapping(method= RequestMethod.GET, value="/v1/licenses/", consumes="application/json")
    public List<License> getAllLicenses(@RequestHeader("Authorization") String token);
    
    @RequestMapping(method = RequestMethod.POST, value="/v1/licenses/")
    public void saveLicenses(@RequestBody License license, @RequestHeader("Authorization") String token);
}
