package com.vaibhav.zookeeper.service_discovery.serviceB.config;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ServiceRegistry {
    private static final Logger logger = LoggerFactory.getLogger(ServiceRegistry.class);
    
    @Autowired 
    private ServiceDiscovery<String> discovery; 
    
    Collection<ServiceInstance<String>> instances = null;
    
    @PostConstruct
    public void init() throws Exception {        
        instances = discovery.queryForInstances("serviceA");        
        logger.info("Instances found " + instances.size());
        
        for(ServiceInstance<String> instance: instances) {
            logger.info("Instance Id: " + instance.getId());
        }
    }
    
    public Map<String, String> callService() throws IllegalAccessException {        
        if(instances == null || instances.size() == 0) {
            throw new IllegalAccessException("No instances of service A available!");
        }
        
        ServiceInstance<String> instance = instances.iterator().next();                
        String uri = instance.buildUriSpec() + "/serviceA/message";
        logger.debug("URI: " + uri);
        
        RestTemplate restTemplate = new RestTemplate();
        
        @SuppressWarnings("unchecked")
        Map<String, String> response = restTemplate.getForObject(uri, LinkedHashMap.class);
        
        return response;
    }
}
