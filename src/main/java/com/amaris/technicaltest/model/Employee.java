package com.amaris.technicaltest.model;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Long id;

    private String imageUrl;

    private String firstName;

    private String lastName;

    private String email;

    private String contactNumber;

    private int age;

    private String dob;

    private double salary;

    private double annualSalary;

    private String address;
}
