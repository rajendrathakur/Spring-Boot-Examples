package com.springboot.examples.entity;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Laptop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String model;

    @NonNull
    private String company;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "laptop", fetch= FetchType.LAZY)
    private Employee employee;
}
