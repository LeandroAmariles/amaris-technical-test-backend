package com.amaris.technicaltest.mapper;


import com.amaris.technicaltest.controller.out.EmployeeResponse;
import com.amaris.technicaltest.model.Employee;
import org.mapstruct.Mapper;

@Mapper
public interface EmployeeMapper {


    EmployeeResponse mapModelToDto(Employee employee);
}
