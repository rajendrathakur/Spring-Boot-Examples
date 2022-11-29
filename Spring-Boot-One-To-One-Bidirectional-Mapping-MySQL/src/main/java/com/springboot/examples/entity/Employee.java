package com.springboot.examples.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;

    @NonNull
    private int salary;

    @OneToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private Laptop laptop;

}
