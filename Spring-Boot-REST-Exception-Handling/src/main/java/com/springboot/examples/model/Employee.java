package com.springboot.examples.model;

import lombok.Data;
import java.util.UUID;

@Data
public class Employee {
    private UUID id;
    private String name;
    private int phoneNumber;

    public Employee() {
        this.id = UUID.randomUUID();
    }
}
