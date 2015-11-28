package com.vaibhav.zookeeper.service_discovery.serviceB.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceBHttpClient {

    public Map<String, String> getMessage(String url) {        
        RestTemplate restTemplate = new RestTemplate();
        
        @SuppressWarnings("unchecked")
        Map<String, String> response = restTemplate.getForObject(url, LinkedHashMap.class);
        
        return response;
    }
}
