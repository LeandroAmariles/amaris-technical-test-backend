package com.amaris.technicaltest.service;

import com.amaris.technicaltest.adapter.EmployeeClient;
import com.amaris.technicaltest.controller.out.EmployeeResponse;
import com.amaris.technicaltest.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
}
