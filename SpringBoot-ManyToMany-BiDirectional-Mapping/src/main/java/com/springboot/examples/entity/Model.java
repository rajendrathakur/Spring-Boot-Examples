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
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String rating;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Manufacturer> manufacturers = new HashSet<>();

}
