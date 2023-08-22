package com.spma.apigatewayserver.filters;

import brave.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import brave.Tracer;
import reactor.core.publisher.Mono;
@Configuration
public class ResponseFilter {

    final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);

    @Autowired
    Tracer tracer;

    @Autowired
    FilterUtils filterUtils;

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                Span currentSpan = tracer.currentSpan();
                if (currentSpan != null) {
                    String traceId = currentSpan.context().traceIdString();
                    logger.debug("Adding the correlation id to the outbound headers. {}", traceId);
                    exchange.getResponse().getHeaders().add(FilterUtils.CORRELATION_ID, traceId);
                } else {
                    logger.debug("No active span found. Unable to add correlation id.");
                }
                logger.debug("Completing outgoing request for {}.", exchange.getRequest().getURI());
            }));
        };
    }
}
