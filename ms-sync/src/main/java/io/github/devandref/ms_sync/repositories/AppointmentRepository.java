package io.github.devandref.ms_sync.repositories;

import io.github.devandref.ms_sync.dtos.appointments.FullAppointmentDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<FullAppointmentDTO, Long> {
}
