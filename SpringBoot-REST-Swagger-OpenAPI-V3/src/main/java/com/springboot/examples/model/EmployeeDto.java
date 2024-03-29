package com.springboot.examples.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;


@Data
@XmlRootElement
@Schema(description = "This is Employee Dto")
public class EmployeeDto {
    private long id;
    @Schema(description = "This is Employee Name")
    private String name;

    private int age;
    private int salary;
    private String city;
}
