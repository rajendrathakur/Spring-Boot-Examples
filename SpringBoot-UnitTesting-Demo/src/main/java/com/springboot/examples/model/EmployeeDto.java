package com.springboot.examples.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Max;
import javax.xml.bind.annotation.XmlRootElement;


@Data
@RequiredArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Schema(description = "This is Employee Dto")
public class EmployeeDto {
    private long id;
    @NonNull
    private String name;
    @NonNull
    @Max(value=40, message = "Age can't be more than 40")
    private int age;
    @NonNull
    private int salary;
    @NonNull
    private String city;
}
