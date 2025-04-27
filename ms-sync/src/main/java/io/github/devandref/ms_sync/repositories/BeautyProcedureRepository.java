package io.github.devandref.ms_sync.repositories;

import io.github.devandref.ms_sync.dtos.BeautyProcedure.BeautyProcedureDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeautyProcedureRepository extends MongoRepository<BeautyProcedureDTO, Long> {
}
