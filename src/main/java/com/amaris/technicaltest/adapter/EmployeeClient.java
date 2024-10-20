package com.amaris.technicaltest.adapter;


import com.amaris.technicaltest.exception.ClientException;
import com.amaris.technicaltest.model.Employee;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.View;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeClient {

        private final WebClient webClient;


        private List<Employee> employees = new ArrayList<>();

        private final ReactiveCircuitBreaker reactiveCircuitBreaker;
        
        
        @Value("${com.technical.test.employee.client.url}")
        private String employeeClientUrl;

        @Value("${com.technical.test.employee.client.path}")
        private String employeeClientPath;


        @Value("${com.technical.test.employee.client.query.param1}")
        private String employeeQueryParam1;

        @Value("${com.technical.test.employee.client.query.param2}")
        private String employeeQueryParam2;



     @Autowired
     public EmployeeClient(WebClient webClient, List<Employee> employeeList, ReactiveCircuitBreakerFactory<?,?> reactiveCircuitBreaker, View error) {
         this.webClient = webClient;
         this.employees = employeeList;
         this.reactiveCircuitBreaker = reactiveCircuitBreaker.create("employeeService");
     }



    public Flux<Employee> listAllEmployees() {
        UriBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(employeeClientUrl).path(employeeClientPath);
        return reactiveCircuitBreaker.run(webClient.get().uri(uriBuilder.toUriString())
                .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                .retrieve().bodyToFlux(Employee.class)
                .doOnNext(response -> log.info("Response from client {}", response))
                .timeout(Duration.ofMillis(5000))
                        .onErrorResume(error -> {
                            log.error("Error during WebClient call: ", error);
                            if (error instanceof TimeoutException) {
                                return Mono.error(new ClientException("Time Out"));
                            } else if (error instanceof WebClientResponseException) {
                                return Mono.error(new ClientException("Client responses with error"));
                            }
                            return Mono.error(error);
                        })
                , this::fallbackGetEmployees).timeout(Duration.ofMillis(10000));
    }

    public Mono<Employee> getEmployee(String id) {
        UriBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(employeeClientUrl)
                .path(employeeClientPath).queryParam(employeeQueryParam1, "1")
                .queryParam(employeeQueryParam2, id);
        return reactiveCircuitBreaker.run(
                webClient.get().uri(uriBuilder.toUriString())
                        .headers(httpHeaders -> {
                            log.info("Consulting uri {}", uriBuilder.toUriString());
                            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                        })
                        .retrieve()
                        .bodyToFlux(Employee.class)
                        .collectList()
                        .flatMap(employees -> {
                            if (!employees.isEmpty()) {
                                return Mono.just(employees.get(0));
                            } else {
                                return Mono.error(new ClientException("No employee found"));
                            }
                        })
                        .doOnNext(response -> log.info("Response from client {}", response))
                        .timeout(Duration.ofMillis(60000))
                        .onErrorResume(error -> {
                            if (error instanceof TimeoutException) {
                                throw new ClientException("Time Out");
                            } else if (error instanceof WebClientResponseException) {
                                throw new ClientException("Client responses with error");
                            }
                            return Mono.error(error);
                        }),
                throwable -> fallbackGetEmployeeById(id, throwable).timeout(Duration.ofMillis(10000))
        );
    }

        @CircuitBreaker(name = "employeeService")
        private Flux<Employee> fallbackGetEmployees(Throwable e) {
            log.error("Fallback method triggered due to: {}", e.getMessage());
            return Flux.fromIterable(new ArrayList<>(employees));
        }

        @CircuitBreaker(name = "employeeService", fallbackMethod = "fallbackGetEmployees")
        private Mono<Employee> fallbackGetEmployeeById(String id, Throwable e) {
            log.error("Fallback method triggered due to: {}", e.getMessage());
            return Mono.just(employees.get(0));
        }




}
