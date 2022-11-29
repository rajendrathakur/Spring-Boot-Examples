package com.springboot.examples.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeInfo {
    private long id;
    private String name;
    private int salary;
    private LaptopInfo laptopInfo;
}
