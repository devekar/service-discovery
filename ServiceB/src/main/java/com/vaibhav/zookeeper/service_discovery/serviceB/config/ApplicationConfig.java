package com.vaibhav.zookeeper.service_discovery.serviceB.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceProvider;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfig {
    public final static String SERVICE_NAME = "serviceA";
    
    @Autowired
    CuratorFramework curatorFrameworkClient;

    @Bean
    public ServiceDiscovery<String> serviceDiscovery() {
        //JsonInstanceSerializer< String > serializer = new JsonInstanceSerializer< String >( String.class );
        
        return ServiceDiscoveryBuilder.builder( String.class )
                .client(curatorFrameworkClient)
                .basePath( "services" )
                .build();  
    }
    
    @Bean
    public ServiceProvider<String> serviceProvider(ServiceDiscovery<String> serviceDiscovery) throws Exception {
        ServiceProvider<String> serviceProvider = serviceDiscovery
                .serviceProviderBuilder()
                .serviceName(SERVICE_NAME)
                .build();
        serviceProvider.start();
        
        return serviceProvider;
    }
}
