package com.springboot.examples.utility;

import com.springboot.examples.entity.Customer;
import com.springboot.examples.entity.Loan;
import com.springboot.examples.repository.CustomerRepository;
import com.springboot.examples.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class LazyTesting {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Transactional
    public void insertCustomerRecords() {
        //insert the data for customer - 1
        Customer customer1 = new Customer("Rajendra", 9515678123l);

        Loan personalLoan = new Loan(250000, LocalDate.parse("2015-02-20"), "Personal Loan", customer1);
        Loan homeLoan = new Loan(780000, LocalDate.parse("2018-05-24"), "Home Loan", customer1);
        Loan goldLoan = new Loan(980000, LocalDate.parse("2019-08-24"),"Gold Laon",  customer1);
        customer1.getLoans().addAll(Arrays.asList(personalLoan, homeLoan, goldLoan));
        customerRepository.save(customer1);

        //insert the data for customer - 2
        Customer customer2 = new Customer("Nikitha", 7893215643l);

        Loan bikeLoan = new Loan(275000, LocalDate.parse("2013-02-20"), "Bike Loan", customer2);
        Loan carLoan = new Loan(480000, LocalDate.parse("2016-05-24"), "Car Loan", customer2);
        customer2.getLoans().addAll(Arrays.asList(bikeLoan, carLoan));
        customerRepository.save(customer2);
    }


    @Transactional
    public void deleteLoanByCustomerId() {
        // Delete third loan record (whose id is 3) of a customer whose id is 1
        Customer customer = customerRepository.findById(1l).get();
        Loan loan = customer.getLoans().stream().filter(l-> l.getId() == 3).findFirst().get();
        customer.getLoans().remove(loan);
        //customer.getLoans().remove(0);
        assertEquals(2, customer.getLoans().size());
    }

    @Transactional
    public void deleteAllLoanByCustomerId() {
        Customer customer = customerRepository.findById(1l).get();
        customer.getLoans().clear();
        assertEquals(0, customer.getLoans().size());
    }
}
