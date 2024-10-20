package com.amaris.technicaltest.controller.out;


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


)
{
}
