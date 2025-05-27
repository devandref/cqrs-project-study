package io.github.devandre.ms_consult.services.impl;

import io.github.devandre.ms_consult.dtos.customers.CustomerDTO;
import io.github.devandre.ms_consult.repositories.CustomerRepository;
import io.github.devandre.ms_consult.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> listAllCustomer() {
        try {
            return this.customerRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error listing all customer");
        }
    }

    @Override
    public List<CustomerDTO> listByNameLikeIgnoreCase(String name) {
        try {
            return this.customerRepository.findByNameLikeIgnoreCase(name);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error listing all customer by name");
        }
    }

    @Override
    public List<CustomerDTO> listByEmailLikeIgnoreCase(String email) {
        try {
            return this.customerRepository.findByEmailLikeIgnoreCase(email);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error listing all customer by email");
        }
    }
}
