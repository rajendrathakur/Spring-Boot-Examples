package com.springboot.examples.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;

    @NonNull
    private long mobileNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<Loan> loans = new ArrayList<>();

    /**
     * If we add @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
     * then it will create a new table with the name customer_loans
     */

    /**
     * If we add @OneToMany along with @JoinColumn like below.
     * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
     * @JoinColumn(name = "customer_id")
     * then it will not create a new table with the name customer_loans
     * It will create a foreign key column customer_id in Loan Table.
     */

}
