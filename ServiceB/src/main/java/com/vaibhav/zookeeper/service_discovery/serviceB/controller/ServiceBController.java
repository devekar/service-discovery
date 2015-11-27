package com.vaibhav.zookeeper.service_discovery.serviceB.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhav.zookeeper.service_discovery.serviceB.config.ServiceRegistry;

@RestController
public class ServiceBController {
    private static final Logger logger = LoggerFactory.getLogger(ServiceBController.class);

    @Autowired
    private ServiceRegistry serviceRegistry;
    
    @RequestMapping(value="/message", method=RequestMethod.GET)
    public Map<String, String> getMessage() throws IllegalAccessException {
        return serviceRegistry.callService();
    }
    
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR) 
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleException(Exception e) {
        logger.error(e.getMessage());
        
        Map<String, String> response = new LinkedHashMap<>();
        response.put("error", e.getMessage());        
        return response;
    }
}
