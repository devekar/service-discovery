package com.vaibhav.zookper.service_discovery.serviceA.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAController {
    @Autowired
    private Environment environment;
    
    @RequestMapping(value="/message", method=RequestMethod.GET)
    public Map<String, String> getMessage() {
        String port = environment.getProperty("server.port");
        
        Map<String, String> message = new LinkedHashMap<>();
        message.put("message", "Greetings from an instance of serviceA at port " + port +"!");
        message.put("messageId", UUID.randomUUID().toString());        
        return message;
    }
}
