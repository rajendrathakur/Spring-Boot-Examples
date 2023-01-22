package com.springboot.examples.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;


@Data
@XmlRootElement
public class EmployeeDto {
    private long id;
    private String name;
    private int age;
    private int salary;
    private String city;
}
