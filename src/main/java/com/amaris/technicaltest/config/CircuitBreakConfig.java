package com.amaris.technicaltest.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakConfig {

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .slidingWindowType( CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
<<<<<<< HEAD
                .slidingWindowSize(20)
                .failureRateThreshold(50.0f)
                .waitDurationInOpenState(Duration.ofMillis(10000))
                .minimumNumberOfCalls(3)
=======
                .slidingWindowSize(10)
                .failureRateThreshold(50.0f)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .permittedNumberOfCallsInHalfOpenState(5)
                .minimumNumberOfCalls(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
>>>>>>> cff0e96d62514e87f4fad1dd69bb871130958161
                .build();

        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(config)
                .build());
    }
}
