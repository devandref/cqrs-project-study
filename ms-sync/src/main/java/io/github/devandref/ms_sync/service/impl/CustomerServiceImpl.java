package io.github.devandref.ms_sync.service.impl;

import io.github.devandref.ms_sync.dtos.customers.CustomerDTO;
import io.github.devandref.ms_sync.repositories.CustomerRepository;
import io.github.devandref.ms_sync.service.CustomerService;
import io.github.devandref.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void saveCustomer(CustomerDTO customer) {
        try {
            SyncLogger.info("Saving customer: " + customer.getId());
            customerRepository.save(customer);
        } catch (Exception ex) {
            SyncLogger.error("Error saving customer: " + ex.getMessage());
            SyncLogger.trace(Arrays.toString(ex.getStackTrace()));
        }

    }
}
