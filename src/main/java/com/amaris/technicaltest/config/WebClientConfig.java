package com.amaris.technicaltest.config;


import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
<<<<<<< HEAD
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
=======
>>>>>>> cff0e96d62514e87f4fad1dd69bb871130958161
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
<<<<<<< HEAD

import javax.net.ssl.SSLException;
=======
>>>>>>> cff0e96d62514e87f4fad1dd69bb871130958161
import java.time.Duration;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;

@Configuration
public class WebClientConfig {

    @Value("${web.client.max.connections}")
    private int maxConnections;

    @Value("${web.client.acquire.timeout}")
    private long acquireTimeout;

    @Bean
<<<<<<< HEAD
    public WebClient webClient() throws SSLException {

        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

=======
    public WebClient webClient() {
>>>>>>> cff0e96d62514e87f4fad1dd69bb871130958161
        HttpClient httpClient = HttpClient.create(ConnectionProvider
                .builder("connectionProvider")
                .maxConnections(maxConnections)
                .maxLifeTime(Duration.ofSeconds(acquireTimeout))
                .pendingAcquireTimeout(Duration.ofMillis(acquireTimeout))
<<<<<<< HEAD
                .build())
                .secure(sslContextSpec -> sslContextSpec.sslContext(sslContext));
=======
                .build());
>>>>>>> cff0e96d62514e87f4fad1dd69bb871130958161

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }



}
