package com.amaris.technicaltest.controller;


import com.amaris.technicaltest.controller.out.EmployeeResponse;
import com.amaris.technicaltest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeService employeeService;

    @GetMapping("/get-all-employees")
    ResponseEntity<Flux<EmployeeResponse>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/get-employee/{id}")
    ResponseEntity<Mono<EmployeeResponse>> getEmployee( @PathVariable int id) {
        return new ResponseEntity<>(employeeService.getEmployeById(id), HttpStatus.OK);
    }

    @GetMapping("/get-employee-annual-salary/{id}")
    ResponseEntity<Mono<EmployeeResponse>> calculateEmployeeAnnualSalary(@PathVariable int id) {
        return new ResponseEntity<>(employeeService.calculateSalary(id), HttpStatus.OK);
    }



}
