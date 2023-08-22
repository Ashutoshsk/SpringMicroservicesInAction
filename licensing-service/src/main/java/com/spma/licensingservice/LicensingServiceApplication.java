package com.spma.licensingservice;


import com.spma.licensingservice.utils.UserContextInterceptor;
import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
public class LicensingServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(LicensingServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LicensingServiceApplication.class, args);
    }

//    @Bean
//    public Consumer<OrganizationChangeModel> loggerSink() {
//        return orgChange -> {
//            logger.debug("Received an {} event for organization id {}",
//                    orgChange.getAction(), orgChange.getOrganizationId());
//        };
//    }



    @Bean
    public Capability capability(final MeterRegistry registry) {
        return new MicrometerCapability(registry);
    }
    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver locale_Resolver = new SessionLocaleResolver();
        locale_Resolver.setDefaultLocale(Locale.US);
        return locale_Resolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setBasenames("messages");
        return messageSource;
    }

//    @SuppressWarnings("unchecked")
//    @LoadBalanced
//    @Bean
//    public RestTemplate getRestTemplate() {
//        RestTemplate template = new RestTemplate();
//        List interceptors = template.getInterceptors();
//        if (interceptors == null) {
//            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
//        } else {
//            interceptors.add(new UserContextInterceptor());
//            template.setInterceptors(interceptors);
//        }
//        return template;
//    }

//    @LoadBalanced
//    @Bean
//    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
//        RestTemplate template = builder.build();
//        template.getInterceptors().add(new UserContextInterceptor());
//        return template;
//    }

    @LoadBalanced
    @Primary
    @Bean
    public RestTemplate getCustomRestTemplate() {
        RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();
        if (interceptors == null) {
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }

        return template;
    }
    @LoadBalanced
    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}