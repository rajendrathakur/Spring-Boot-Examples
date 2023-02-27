package com.springboot.examples.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;


@Data
@NoArgsConstructor
public class EmployeeDto {
    private long id;
    private String name;
    private int age;
    private int salary;
    private String city;
    private int pfAmount;
    private int pensionAmount;
    private int statusCode;
    private String status;
}
