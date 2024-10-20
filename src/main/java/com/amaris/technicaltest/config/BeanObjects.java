package com.amaris.technicaltest.config;


import com.amaris.technicaltest.model.Employee;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Configuration
public class BeanObjects {

    private final List<Employee>  employees = new ArrayList<>();

    @Bean
    @ConfigurationProperties(prefix = "com.technical.test.employees.employee1")
    public Employee employee1(){return new Employee();}

    @Bean
    @ConfigurationProperties(prefix = "com.technical.test.employees.employee2")
    public Employee employee2(){return new Employee();}

    @Bean
    @ConfigurationProperties(prefix = "com.technical.test.employees.employee3")
    public Employee employee3(){return new Employee();}

    @Bean
    @ConfigurationProperties(prefix = "com.technical.test.employees.employee4")
    public Employee employee4(){return new Employee();}

    @Bean
    @ConfigurationProperties(prefix = "com.technical.test.employees.employee5")
    public Employee employee5(){return new Employee();}

    @Bean
    @ConfigurationProperties(prefix = "com.technical.test.employees.employee6")
    public Employee employee6(){return new Employee();}

    @Bean
    @ConfigurationProperties(prefix = "com.technical.test.employees.employee7")
    public Employee employee7(){return new Employee();}

    @Bean
    @ConfigurationProperties(prefix = "com.technical.test.employees.employee8")
    public Employee employee8(){return new Employee();}

    @Bean
    @ConfigurationProperties(prefix = "com.technical.test.employees.employee9")
    public Employee employee9(){return new Employee();}


    @Bean
    @ConfigurationProperties(prefix = "com.technical.test.employees.employee10")
    public Employee employee10(){return new Employee();}



    @Bean
    public List<Employee> getEmployees(Map<String, Employee> employeeMap) {
        employeeMap.forEach((key, value) -> employees.add(value));
        return employees;
    }

}
