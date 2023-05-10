package com.spma.licensingservice;

import java.util.Locale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@RefreshScope
public class LicensingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicensingServiceApplication.class, args);
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