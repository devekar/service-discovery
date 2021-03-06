package com.vaibhav.zookper.service_discovery.serviceA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
@EnableDiscoveryClient
@EnableAutoConfiguration
public class ServiceAApplication extends SpringBootServletInitializer {

    public static void main( String[] args ) {
        SpringApplication.run(ServiceAApplication.class, args);
    }
}
