package com.springboot.examples.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.OverridesAttribute;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private int id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email is not valid")
    private String email;

    @Min(message = "Salary must be atleast 40k", value = 40000)
    @Max(message = "Salary must be less than 100k", value = 100000)
    private int salary;

    @Valid
    private AddressDto addressDto;
}
