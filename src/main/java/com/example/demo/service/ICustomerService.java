package com.example.demo.service;

import com.example.demo.entity.Customer;

import java.util.Optional;

public interface ICustomerService {
    Iterable<Customer>findAll();

    Optional<Customer> findCustomerById(Long id);

    Customer save(Customer customer);

    void remove(Long id);
}
