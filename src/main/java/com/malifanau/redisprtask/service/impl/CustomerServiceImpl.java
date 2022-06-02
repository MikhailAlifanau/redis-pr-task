package com.malifanau.redisprtask.service.impl;

import com.malifanau.redisprtask.exception.CustomerNotFoundException;
import com.malifanau.redisprtask.model.Customer;
import com.malifanau.redisprtask.repository.CustomerRepository;
import com.malifanau.redisprtask.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateById(Long id, Customer customer) {
        Customer target = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer was not found"));
        target.setName(customer.getName());
        return customerRepository.save(target);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer was not found")
        );
    }
}
