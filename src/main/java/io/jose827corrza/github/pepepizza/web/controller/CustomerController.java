package io.jose827corrza.github.pepepizza.web.controller;

import io.jose827corrza.github.pepepizza.persistence.entity.CustomerEntity;
import io.jose827corrza.github.pepepizza.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<CustomerEntity> getCustomerByPhoneNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(this.customerService.findCustomerByPhoneNumber(phoneNumber));
    }

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAllCustomers() {
        return ResponseEntity.ok(this.customerService.getAllCustomers());
    }
}
