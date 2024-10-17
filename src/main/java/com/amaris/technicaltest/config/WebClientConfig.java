package com.amaris.technicaltest.config;


import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import java.time.Duration;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;

@Configuration
public class WebClientConfig {

    @Value("${web.client.max.connections}")
    private int maxConnections;

    @Value("${web.client.acquire.timeout}")
    private long acquireTimeout;

    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.create(ConnectionProvider
                .builder("connectionProvider")
                .maxConnections(maxConnections)
                .maxLifeTime(Duration.ofSeconds(acquireTimeout))
                .pendingAcquireTimeout(Duration.ofMillis(acquireTimeout))
                .build());

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }



}
