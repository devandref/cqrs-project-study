package io.github.devandre.ms_consult.repositories;

import io.github.devandre.ms_consult.dtos.beautyprocedures.BeautyProcedureDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BeautyProcedureRepository extends MongoRepository<BeautyProcedureDTO, Long> {

    @Query("{ 'name': { $regex: ?0, $options: 'i' }}")
    List<BeautyProcedureDTO> findByNameIgnoreCase(String name);

    @Query("{ 'description': { $regex: ?0, $options: 'i' }}")
    List<BeautyProcedureDTO> findByDescriptionIgnoreCase(String description);

}
