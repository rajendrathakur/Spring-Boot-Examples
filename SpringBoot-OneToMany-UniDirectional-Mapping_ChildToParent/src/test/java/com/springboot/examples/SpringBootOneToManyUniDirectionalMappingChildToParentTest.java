package com.springboot.examples;

import com.springboot.examples.entity.Customer;
import com.springboot.examples.entity.Loan;
import com.springboot.examples.repository.LoanRepository;
import com.springboot.examples.utility.LazyTesting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class SpringBootOneToManyUniDirectionalMappingChildToParentTest {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LazyTesting lazyTesting;


    @Test
    public void insertRecords() {
        //insert the data for customer - 1
        Customer customer1 = new Customer("Rajendra", 9515678123l);

        Loan personalLoan1 = new Loan(250000, LocalDate.parse("2015-02-20"), customer1);
        Loan homeLoan1 = new Loan(780000, LocalDate.parse("2018-05-24"), customer1);
        Loan carLoan1 = new Loan(980000, LocalDate.parse("2019-08-24"), customer1);
        loanRepository.saveAll(Arrays.asList(personalLoan1, homeLoan1, carLoan1));

        //insert the data for customer - 2
        Customer customer2 = new Customer("Nikitha", 7893215643l);

        Loan personalLoan2 = new Loan(275000, LocalDate.parse("2013-02-20"), customer2);
        Loan homeLoan2 = new Loan(480000, LocalDate.parse("2016-05-24"), customer2);
        loanRepository.saveAll(Arrays.asList(personalLoan2, homeLoan2));

    }

    @Test
    public void fetchLoan() {
        lazyTesting.fetchLoanData();
    }


    @Test
    public void deleteLoanByLoanId() {
        loanRepository.deleteById(1l);
        Optional<Loan> loan = loanRepository.findById(1L);
        assertFalse(loan.isPresent());
    }

    @Test
    public void deleteLoansByCustomerId() {
        loanRepository.deleteByCustomerId(1l);
        List<Loan> loans = loanRepository.findAll();
        assertEquals(2, loans.size());
    }

}