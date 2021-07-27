package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomerService implements ICustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }
}
