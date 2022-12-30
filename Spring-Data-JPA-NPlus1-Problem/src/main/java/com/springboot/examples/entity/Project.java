package com.springboot.examples.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;

    @NonNull
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Manager manager;

}
