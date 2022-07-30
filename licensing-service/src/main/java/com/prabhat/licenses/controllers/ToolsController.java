package com.prabhat.licenses.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prabhat.licenses.services.DiscoveryService;

@RestController
@RequestMapping(value="v1/tools")
public class ToolsController {
    @Autowired
    private DiscoveryService discoveryService;

    @RequestMapping(value="/eureka/services",method = RequestMethod.GET)
    public List<String> getEurekaServices() {

        return discoveryService.getEurekaServices();
    }
    
    @RequestMapping(value="/create",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('Role_USER')")
    public String create() {
        return "Test create";
    }
    
    @RequestMapping(value="/read",method = RequestMethod.GET)
    public String read() {
        return "Test read";
    }
}
