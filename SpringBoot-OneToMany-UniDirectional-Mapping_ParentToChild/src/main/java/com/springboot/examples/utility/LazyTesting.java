package com.springboot.examples.utility;

import com.springboot.examples.entity.Customer;
import com.springboot.examples.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class LazyTesting {

    @Autowired
    private CustomerRepository customerRepository;


    @Transactional
    public void fetchCustomerData() {
        Customer customer = customerRepository.findById(1l).get();
        assertEquals("Rajendra", customer.getName());
        assertEquals(3, customer.getLoans().size());
    }

    @Transactional
    public void deleteLoanByCustomerId() {
        Customer customer = customerRepository.findById(2l).get();
        customer.getLoans().remove(0);
        assertEquals(1, customer.getLoans().size());
    }


}
