package com.spma.licensingservice.service.client;

import com.spma.licensingservice.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class OrganizationWebClientClient {

    @Autowired
    private WebClient webClient;

    public Organization getOrganization(String organizationId) {
        return webClient.get()
                .uri("http://organization-service/v1/organization/{organizationId}", organizationId)
                .exchange()
                .flatMap(response -> response.bodyToMono(Organization.class))
                .block();
    }
}
