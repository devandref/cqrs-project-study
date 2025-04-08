package io.github.devandref.beautique.service.impl;

import io.github.devandref.beautique.component.ConverterUtilComponent;
import io.github.devandref.beautique.dto.CustomerDTO;
import io.github.devandref.beautique.entities.CustomerEntity;
import io.github.devandref.beautique.repository.CustomerRepository;
import io.github.devandref.beautique.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public void delete(Long id) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);
        if (customerEntityOptional.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }
        customerRepository.delete(customerEntityOptional.get());
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(customerDTO.getId());
        if (customerEntityOptional.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }
        CustomerEntity customerEntity = converterUtil.convertToSource(customerDTO);

        customerEntity.setAppointments(customerEntityOptional.get().getAppointments());
        customerEntity.setCreatedAt(customerEntityOptional.get().getCreatedAt());

        return converterUtil.convertToTarget(customerRepository.save(customerEntity));
    }


}
