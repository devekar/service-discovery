package com.vaibhav.zookper.service_discovery.serviceA.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Autowired
    CuratorFramework curatorFrameworkClient;

    @Bean
    public ServiceDiscovery<String> discovery() {
        //JsonInstanceSerializer< String > serializer = new JsonInstanceSerializer< String >( String.class );

        return ServiceDiscoveryBuilder.builder( String.class )
                .client(curatorFrameworkClient)
                .basePath( "services" )                
                .build();  
    }
}
