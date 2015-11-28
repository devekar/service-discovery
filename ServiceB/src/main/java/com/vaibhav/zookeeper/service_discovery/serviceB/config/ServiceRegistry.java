package com.vaibhav.zookeeper.service_discovery.serviceB.config;

import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServiceRegistry {
    private static final Logger logger = LoggerFactory.getLogger(ServiceRegistry.class);
    
    @Autowired
    private ServiceProvider<String> serviceProvider;    
    
    public String getServiceUrl() throws Exception {    
        // Will cycle through available instances
        ServiceInstance<String> instance = serviceProvider.getInstance();
        
        String url = instance.buildUriSpec() + "/serviceA/message";
        logger.debug("URL: " + url);
        
        return url;
    }
}
