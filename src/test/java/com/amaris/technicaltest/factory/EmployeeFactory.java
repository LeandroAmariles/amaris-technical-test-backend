package com.amaris.technicaltest.factory;

import com.amaris.technicaltest.model.Employee;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;


@Getter
@RequiredArgsConstructor
public enum EmployeeFactory {

    NORMAL_EMPLOYEE(() -> Employee.builder()
            .id(1L)
            .imageUrl("imageTest")
            .firstName("Test")
            .lastName("testLast")
            .email("email@Test")
            .annualSalary(1.00)
            .contactNumber("12345")
            .age(30)
            .dob("dobTest")
            .salary(1235.00)
            .address("av-siempre-viva-123")
            .build());

    private final Supplier<Employee> employee;
}
