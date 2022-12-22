package com.springboot.examples.repository;

import com.springboot.examples.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Transactional
    void deleteByCustomerId(long customerId);
}
