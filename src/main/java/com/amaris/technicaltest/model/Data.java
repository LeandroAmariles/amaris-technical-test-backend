package com.amaris.technicaltest.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Data {

    private String employeeName;

    private double employeeSalary;

    private double annualSalary;

    private int employeeAge;

    private String profileImageUrl;
}
