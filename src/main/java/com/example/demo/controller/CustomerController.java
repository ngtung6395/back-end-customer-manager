package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<Customer>> showAllCustomer(){
        List<Customer> customerList = (List<Customer>) customerService.findAll();
        if(customerList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerList,HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id){
        Optional<Customer> customerOptional = customerService.findCustomerById(id);
        if(!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerOptional.get(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Customer>createCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.save(customer),HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    //request body chu ko phai model atrribute
    public ResponseEntity<Customer>editCustomerById(@PathVariable Long id, @RequestBody  Customer customer){
        Optional<Customer> customerOptional = customerService.findCustomerById(id);
        System.out.println("day la custome cua tao"+customer);
        if(!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        customer.setId(customerOptional.get().getId());
        //ko can vi thang customer co id di kem roi
        customerService.save(customer);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer>delelteCustomerById(@PathVariable Long id){
        Optional<Customer>customerOptional= customerService.findCustomerById(id);
        if(!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
