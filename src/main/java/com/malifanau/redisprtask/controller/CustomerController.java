package com.malifanau.redisprtask.controller;

import com.malifanau.redisprtask.model.Customer;
import com.malifanau.redisprtask.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAll() {
        log.info("processing get all customers request");
        return customerService.getAll();
    }

    @PostMapping("/{id}")
    public Customer updateById(@PathVariable Long id, @RequestBody Customer customer) {
        log.info("processing update customer request");
        return customerService.updateById(id, customer);

    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        log.info("processing get customer by id request");
        return customerService.getById(id);
    }
}
