package com.springboot.examples.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Laptop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String model;

    @NonNull
    private String company;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Employee employee;
}
