package io.github.devandref.beautique.service.impl;

import io.github.devandref.beautique.component.ConverterUtilComponent;
import io.github.devandref.beautique.dto.CustomerDTO;
import io.github.devandref.beautique.entities.CustomerEntity;
import io.github.devandref.beautique.repository.CustomerRepository;
import io.github.devandref.beautique.service.BrokerService;
import io.github.devandref.beautique.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ConverterUtilComponent<CustomerEntity, CustomerDTO> converterUtil = new ConverterUtilComponent<>(CustomerEntity.class, CustomerDTO.class);
    private final BrokerService brokerService;

    public CustomerServiceImpl(CustomerRepository customerRepository, BrokerService brokerService) {
        this.customerRepository = customerRepository;
        this.brokerService = brokerService;
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = converterUtil.convertToSource(customerDTO);
        CustomerEntity newCustomerEntity = customerRepository.save(customerEntity);
        sendCustomerToQueue(newCustomerEntity);
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
        sendCustomerToQueue(customerEntity);
        return converterUtil.convertToTarget(customerRepository.save(customerEntity));
    }

    private void sendCustomerToQueue(CustomerEntity customerEntity) {
        var customerDTO = CustomerDTO
                .builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .email(customerEntity.getEmail())
                .phone(customerEntity.getPhone())
                .build();
        brokerService.send("customer", customerDTO);
    }


}
