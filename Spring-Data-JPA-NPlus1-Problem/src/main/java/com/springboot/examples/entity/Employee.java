package com.springboot.examples.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;

    @NonNull
    private int salary;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Project> projects = new HashSet<>();

}
