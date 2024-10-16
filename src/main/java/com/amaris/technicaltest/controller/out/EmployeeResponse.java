package com.amaris.technicaltest.controller.out;

import com.amaris.technicaltest.model.Data;

public  record EmployeeResponse (

        String status,

        Data data
)
{
}
