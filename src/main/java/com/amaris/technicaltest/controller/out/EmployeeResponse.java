package com.amaris.technicaltest.controller.out;

<<<<<<< HEAD
public  record EmployeeResponse (

         Long id,

         String imageUrl,

         String firstName,

         String lastName,

         String email,

         double annualSalary,

         String contactNumber,

         int age,

         String dob,

         double salary,

         String address
=======
import com.amaris.technicaltest.model.Data;

public  record EmployeeResponse (

        Long id,

        String status,

        Data data
>>>>>>> cff0e96d62514e87f4fad1dd69bb871130958161
)
{
}
