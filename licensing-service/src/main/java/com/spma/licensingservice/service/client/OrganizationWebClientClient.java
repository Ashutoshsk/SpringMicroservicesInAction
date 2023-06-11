package com.spma.licensingservice.service.client;

import com.spma.licensingservice.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class OrganizationWebClientClient {
    @Autowired
    private WebClient webClient;
    @Autowired
    private OAuth2ResourceServerProperties oauth2ResourceServerProperties;

//    public Organization getOrganization(String organizationId) {
//        return webClient.get()
//                .uri("http://organization-service/v1/organization/{organizationId}", organizationId)
//                .exchange().flatMap(response -> response.bodyToMono(Organization.class)).block();
//    }

    public Organization getOrganization(String organizationId) {
        Mono<Organization> organizationMono = webClient.get()
                .uri("http://localhost:8072/organization/v1/organization/{organizationId}", organizationId)
                .exchange()
                .flatMap(it -> it.bodyToMono(Organization.class));
        return organizationMono.block();
    }

//    public Mono<Organization> getOrganization(String organizationId) {
//        return webClient.get()
//                .uri(oauth2ResourceServerProperties.getTokenUri())
//                .headers(headers -> {
//                    headers.setBearerAuth(oauth2ResourceServerProperties.getClientId() + ":" + oauth2ResourceServerProperties.getClientSecret());
//                })
//                .exchangeToMono(response -> response.bodyToMono(OAuth2AccessToken.class))
//                .flatMap(accessToken -> webClient.get()
//                        .uri("http://gateway:8072/organization/v1/organization/{organizationId}", organizationId)
//                        .header("Authorization", "Bearer " + accessToken.getTokenValue())
//                        .exchangeToMono(response -> response.bodyToMono(Organization.class)))
//                .block();
//    }
}