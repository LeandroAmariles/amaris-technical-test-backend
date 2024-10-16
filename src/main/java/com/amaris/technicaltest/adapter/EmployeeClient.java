package com.amaris.technicaltest.adapter;


import com.amaris.technicaltest.exception.ClientException;
import com.amaris.technicaltest.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeClient {

        private final WebClient webClient;
        
        
        @Value("${com.technical.test.employee.client.url}")
        private String employeeClientUrl;

        @Value("${com.technical.test.employee.client.path}")
        private String employeeClientPath;


        public Flux<Employee> listAllEmployees() {
                UriBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(employeeClientUrl).path(employeeClientPath);
                return webClient.get().uri(uriBuilder.toUriString())
                        .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                        .retrieve().bodyToFlux(Employee.class)
                        .doOnNext(response -> log.info("Response from client {}", response))
                        .timeout(Duration.ofMillis(60000))
                        .onErrorMap(error -> {
                                if (error instanceof TimeoutException) {
                                        return new TimeoutException("Time Out");
                                } else if (error instanceof WebClientResponseException) {
                                        return new ClientException("Client responses with error");
                                }
                                return error;
                        });
        }

        public Mono<Employee> getEmployee(String id) {
                UriBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(employeeClientUrl)
                        .path(employeeClientPath.concat("/".concat(id)));
                return webClient.get().uri(uriBuilder.toUriString())
                        .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                        .retrieve().bodyToMono(Employee.class)
                        .doOnNext(response -> log.info("Response from client {}", response))
                        .timeout(Duration.ofMillis(60000))
                        .onErrorMap(error -> {
                                if (error instanceof TimeoutException) {
                                        return new TimeoutException("Time Out");
                                } else if (error instanceof WebClientResponseException) {
                                        return new ClientException("Client responses with error");
                                }
                                return error;
                        });
        }




}
