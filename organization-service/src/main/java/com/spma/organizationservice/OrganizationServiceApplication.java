package com.spma.organizationservice;

import com.spma.organizationservice.utils.UserContextInterceptor;
import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
public class OrganizationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationServiceApplication.class, args);
    }

//    @LoadBalanced
//    @Bean
//    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
//        RestTemplate template = builder.build();
//        template.getInterceptors().add(new UserContextInterceptor());
//        return template;
//    }
//    @LoadBalanced
//    @Bean
//    public RestTemplate getRestTemplate(RestTemplateBuilder builder)
//    {
//        return builder.build();
//    }
    @Bean
    public Capability capability(final MeterRegistry registry) {
        return new MicrometerCapability(registry);
    }
}
