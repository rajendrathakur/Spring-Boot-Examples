package com.springboot.examples.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class EmployeeDto {
    private long id;
    private final String name;
    private final int age;
    private final int salary;
    private final String city;
}
