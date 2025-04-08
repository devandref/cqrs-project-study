package io.github.devandref.beautique.repository;

import io.github.devandref.beautique.entities.BeautyProceduresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeautyProcedureRepository extends JpaRepository<BeautyProceduresEntity, Long> {
}
