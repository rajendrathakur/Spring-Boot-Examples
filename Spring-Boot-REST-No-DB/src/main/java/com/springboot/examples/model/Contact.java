package com.springboot.examples.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Contact {
    private UUID id;
    private String name;
    private int phoneNumber;

    public Contact() {
        this.id = UUID.randomUUID();
    }
}
