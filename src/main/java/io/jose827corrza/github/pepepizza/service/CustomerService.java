package io.jose827corrza.github.pepepizza.service;

import io.jose827corrza.github.pepepizza.persistence.entity.CustomerEntity;
import io.jose827corrza.github.pepepizza.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity findCustomerByPhoneNumber(String phone) {
        return this.customerRepository.findByPhone(phone);
    }

    public List<CustomerEntity> getAllCustomers() {
        return this.customerRepository.findAll();
    }
}
