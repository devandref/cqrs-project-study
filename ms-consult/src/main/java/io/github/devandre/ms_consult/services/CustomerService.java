package io.github.devandre.ms_consult.services;

import io.github.devandre.ms_consult.dtos.customers.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> listAllCustomer();
    List<CustomerDTO> listByNameLikeIgnoreCase(String name);
    List<CustomerDTO> listByEmailLikeIgnoreCase(String email);

}
