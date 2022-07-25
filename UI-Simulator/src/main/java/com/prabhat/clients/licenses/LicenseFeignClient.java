package com.prabhat.clients.licenses;


import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prabhat.model.License;

@FeignClient("licensingservice")
public interface LicenseFeignClient {
	
    @RequestMapping(method= RequestMethod.GET, value="/v1/licenses/", consumes="application/json")
    public List<License> getAllLicenses(@RequestHeader("Authorization") String token);
}
