package io.github.devandre.ms_consult.repositories;

import io.github.devandre.ms_consult.dtos.customers.CustomerDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CustomerRepository extends MongoRepository<CustomerDTO, Long> {

    @Query("{ 'name': { $regex: ?0, $options: 'i' }}")
    List<CustomerDTO> findByNameLikeIgnoreCase(String name);

    @Query("{ 'name': { $regex: ?0, $options: 'i' }}")
    List<CustomerDTO> findByEmailLikeIgnoreCase(String email);

}
