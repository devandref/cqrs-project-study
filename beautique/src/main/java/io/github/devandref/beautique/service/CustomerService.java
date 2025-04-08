package io.github.devandref.beautique.service;

import io.github.devandref.beautique.dto.CustomerDTO;

public interface CustomerService {

    CustomerDTO create(CustomerDTO customerDTO);
    void delete(Long id);
    CustomerDTO update(CustomerDTO customerDTO);
}
