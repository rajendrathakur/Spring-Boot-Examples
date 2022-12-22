package com.springboot.examples.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private int amount;

    @NonNull
    private LocalDate issuedDate;

    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Customer customer;

}
