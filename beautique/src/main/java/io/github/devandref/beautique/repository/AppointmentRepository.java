package io.github.devandref.beautique.repository;

import io.github.devandref.beautique.entities.AppointmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentsEntity, Long> {
}
