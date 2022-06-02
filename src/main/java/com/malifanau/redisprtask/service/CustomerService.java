package com.malifanau.redisprtask.service;

import com.malifanau.redisprtask.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAll();

    Customer updateById(Long id, Customer customer);

    Customer getById(Long id);

}
