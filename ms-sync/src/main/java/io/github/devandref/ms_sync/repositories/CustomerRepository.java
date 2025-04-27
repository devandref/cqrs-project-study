package io.github.devandref.ms_sync.repositories;

import io.github.devandref.ms_sync.dtos.customers.CustomerDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerDTO, Long> {
}
