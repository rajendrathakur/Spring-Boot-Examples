package com.springboot.examples.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;


@Data
@RequiredArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class EmployeeDto {
    private long id;
    @NonNull
    private String name;
    @NonNull
    private int age;
    @NonNull
    private int salary;
    @NonNull
    private String city;
}
