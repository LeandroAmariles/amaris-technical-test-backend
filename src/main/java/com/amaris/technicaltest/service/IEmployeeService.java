package com.amaris.technicaltest.service;

import com.amaris.technicaltest.controller.out.EmployeeResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEmployeeService {

    public Flux<EmployeeResponse> getAllEmployees();

    public Mono<EmployeeResponse> getEmployeById(Integer id);

    public Mono<EmployeeResponse> calculateSalary(Integer id);
}
