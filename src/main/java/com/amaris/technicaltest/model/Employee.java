package com.amaris.technicaltest.model;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Long id;

    private String status;

    private Data data;
}
