package com.vaibhav.zookper.service_discovery.serviceA.config;

import java.net.InetAddress;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


@Service
public class SeviceRegistry {
    private static final Logger logger = LoggerFactory.getLogger(SeviceRegistry.class);
    
    @Autowired
    private Environment environment;
    
    @Autowired 
    private ServiceDiscovery<String> discovery; 
    
    @PostConstruct
    public void init() throws Exception {    
        String serviceName = "serviceA";
        String host = InetAddress.getLocalHost().getHostAddress();
        int port = environment.getProperty("server.port", Integer.class);
        
        UriSpec uriSpec = new UriSpec("http://" + host + ":" + port); // Scheme, address and port
        
        final ServiceInstance<String> instance = 
            ServiceInstance.<String>builder()
                .name(serviceName)
                .payload("This is service A to be consumed")
                .address(host)
                .port(port)
                .uriSpec(uriSpec)
                .build();
        
        logger.info("Registering instance " + instance.getId());
        discovery.registerService( instance );
    }
}
