package com.springboot.examples.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;

    @NonNull
    private int age;

    @NonNull
    private boolean active;

    @NonNull
    private LocalDate dob;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Book> books = new ArrayList<>();


}
