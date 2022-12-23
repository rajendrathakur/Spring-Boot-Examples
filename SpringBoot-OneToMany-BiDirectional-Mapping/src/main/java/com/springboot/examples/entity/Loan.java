package com.springboot.examples.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
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
    private String loanType;

    @NonNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id", insertable = false, updatable = false)
    private Customer customer;

}
