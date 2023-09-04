package com.spma.licensingservice;

import com.spma.licensingservice.config.RedisConfiguration;
import com.spma.licensingservice.events.model.OrganizationChangeModel;
import com.spma.licensingservice.repository.OrganizationRedisRepository;
import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.function.Consumer;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
@EnableRedisRepositories("com.spma.licensingservice.repository")
@EnableJpaRepositories("com.spma.licensingservice.repository")
public class LicensingServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(LicensingServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LicensingServiceApplication.class, args);
    }


    @Bean
    public Consumer<OrganizationChangeModel> loggerSink() {
        return orgChange -> {
            logger.info("Received an {} event for organization id {}", orgChange.getAction(), orgChange.getOrganizationId());
        };
    }


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
}