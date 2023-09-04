package com.spma.licensingservice.service.client;

import com.spma.licensingservice.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class OrganizationWebClientClient {
    @Autowired
    private WebClient webClient;

    public Organization getOrganization(String organizationId) {
        Mono<Organization> organizationMono = webClient.get()
                .uri("http://localhost:8072/organization/v1/organization/{organizationId}", organizationId)
                .exchange()
                .flatMap(it -> it.bodyToMono(Organization.class));
        return organizationMono.block();
    }
}