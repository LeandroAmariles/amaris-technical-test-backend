package com.amaris.technicaltest;

import com.amaris.technicaltest.adapter.EmployeeClient;
import com.amaris.technicaltest.controller.out.EmployeeResponse;
import com.amaris.technicaltest.mapper.EmployeeMapper;
import com.amaris.technicaltest.model.Employee;
import com.amaris.technicaltest.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.amaris.technicaltest.factory.EmployeeFactory.NORMAL_EMPLOYEE;
import static com.amaris.technicaltest.factory.ResponseFactory.RESPONSE_NORMAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeClient employeeClient;

    @Mock
    private EmployeeMapper employeeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {

        Employee employee = NORMAL_EMPLOYEE.getEmployee().get();
        EmployeeResponse employeeResponse = RESPONSE_NORMAL.getEmployeeResponse().get();
        when(employeeClient.listAllEmployees()).thenReturn(Flux.just(employee));
        when(employeeMapper.mapModelToDto(any(Employee.class))).thenReturn(employeeResponse);


        Flux<EmployeeResponse> result = employeeService.getAllEmployees();

        result.as(StepVerifier::create)
                .expectNext(employeeResponse)
                .expectComplete()
                .verify();

        verify(employeeClient).listAllEmployees();

    }

    @Test
    void testGetEmployeeById() {

        Integer id = 1;
        Employee employee = NORMAL_EMPLOYEE.getEmployee().get();
        EmployeeResponse employeeResponse = RESPONSE_NORMAL.getEmployeeResponse().get();
        when(employeeClient.getEmployee(String.valueOf(id))).thenReturn(Mono.just(employee));
        when(employeeMapper.mapModelToDto(employee)).thenReturn(employeeResponse);
        Mono<EmployeeResponse> result = employeeService.getEmployeById(id);

        assertEquals(result.block(), employeeResponse);

        verify(employeeClient).getEmployee(String.valueOf(id));

    }

    @Test
    void testCalculateSalary() {

        Integer id = 1;
        Employee employee = NORMAL_EMPLOYEE.getEmployee().get();
        employee.setSalary(1000.0);
        EmployeeResponse employeeResponse = RESPONSE_NORMAL.getEmployeeResponse().get();
        when(employeeClient.getEmployee(String.valueOf(id))).thenReturn(Mono.just(employee));
        when(employeeMapper.mapModelToDto(employee)).thenReturn(employeeResponse);


        Mono<EmployeeResponse> result = employeeService.calculateSalary(id);


        assertNotEquals(result.block(), employeeResponse);
        assertEquals(12000.0, employee.getAnnualSalary());

        verify(employeeClient).getEmployee(String.valueOf(id));

    }
}
