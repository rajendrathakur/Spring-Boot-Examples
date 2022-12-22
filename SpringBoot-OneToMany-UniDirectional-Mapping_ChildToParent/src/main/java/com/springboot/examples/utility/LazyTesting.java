package com.springboot.examples.utility;

import com.springboot.examples.entity.Loan;
import com.springboot.examples.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class LazyTesting {

    @Autowired
    private LoanRepository loanRepository;

    @Transactional
    public void fetchLoanData() {
        Loan loan = loanRepository.findById(1l).get();
        assertEquals(250000, loan.getAmount());
        assertEquals("Rajendra", loan.getCustomer().getName());
    }


}
