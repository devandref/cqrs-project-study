package io.github.devandref.ms_sync.service;

import io.github.devandref.ms_sync.dtos.customers.CustomerDTO;

public interface CustomerService {
    void saveCustomer(CustomerDTO customer);
}
