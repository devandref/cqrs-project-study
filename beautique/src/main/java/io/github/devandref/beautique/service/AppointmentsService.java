package io.github.devandref.beautique.service;

import io.github.devandref.beautique.dto.AppointmentDTO;

public interface AppointmentsService {

    AppointmentDTO create(AppointmentDTO appointmentDTO);
    AppointmentDTO update(AppointmentDTO appointmentDTO);
    void deleteById(Long id);
    AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentDTO);

}
