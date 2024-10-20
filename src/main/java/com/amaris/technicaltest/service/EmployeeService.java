package com.amaris.technicaltest.service;

import com.amaris.technicaltest.adapter.EmployeeClient;
import com.amaris.technicaltest.controller.out.EmployeeResponse;
import com.amaris.technicaltest.exception.ClientException;
import com.amaris.technicaltest.mapper.EmployeeMapper;
import com.amaris.technicaltest.model.Employee;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeClient employeeClient;


    private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);


    @Override
    public Flux<EmployeeResponse> getAllEmployees() {
        return employeeClient.listAllEmployees().map(
                employeeMapper::mapModelToDto);
    }

    @Override
    public Mono<EmployeeResponse> getEmployeById(Integer id) {
        return employeeClient.getEmployee(String.valueOf(id))
                .map(employeeMapper::mapModelToDto);
    }

    @Override
    public Mono<EmployeeResponse> calculateSalary(Integer id) {
        return employeeClient.getEmployee(String.valueOf(id))
                .map(employee -> {
<<<<<<< HEAD
                    employee.setAnnualSalary(employee.getSalary() * 12);
=======
                    employee.getData().setAnnualSalary(employee.getData().getEmployeeSalary() * 12);
>>>>>>> cff0e96d62514e87f4fad1dd69bb871130958161
                    return employeeMapper.mapModelToDto(employee);
                });
    }

}
