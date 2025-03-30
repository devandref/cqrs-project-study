package io.github.devandref.beautique.service.impl;

import io.github.devandref.beautique.component.ConverterUtilComponent;
import io.github.devandref.beautique.dto.CustomerDTO;
import io.github.devandref.beautique.entities.CustomerEntity;
import io.github.devandref.beautique.repository.CustomerRepository;
import io.github.devandref.beautique.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ConverterUtilComponent<CustomerEntity, CustomerDTO> converterUtil = new ConverterUtilComponent<>(CustomerEntity.class, CustomerDTO.class);

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = converterUtil.convertToSource(customerDTO);
        CustomerEntity newCustomerEntity = customerRepository.save(customerEntity);
        return converterUtil.convertToTarget(newCustomerEntity);
    }

}
