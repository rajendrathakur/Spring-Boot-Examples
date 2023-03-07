package com.springboot.examples.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Data
public class EmployeeDto implements Serializable {
    private long id;
    private String name;
    private int age;
    private int salary;
    private String city;
}
