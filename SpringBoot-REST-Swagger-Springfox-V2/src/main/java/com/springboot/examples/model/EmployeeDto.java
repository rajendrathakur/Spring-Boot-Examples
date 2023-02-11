package com.springboot.examples.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;


@Data
@XmlRootElement
@ApiModel(value = "This is Employee Dto")
public class EmployeeDto {
    private long id;
    @ApiModelProperty(value = "This is employee name")
    private String name;
    private int age;
    private int salary;
    private String city;
}
