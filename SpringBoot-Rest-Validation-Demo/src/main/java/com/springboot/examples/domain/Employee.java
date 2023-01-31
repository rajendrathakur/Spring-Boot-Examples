package com.springboot.examples.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable
{
    private static final long serialVersionUID = 8385741327704693623L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private int salary;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;
}
