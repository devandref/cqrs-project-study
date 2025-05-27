package io.github.devandre.ms_consult.services;

import io.github.devandre.ms_consult.dtos.appointments.FullAppointmentDTO;

import java.util.List;

public interface AppointmentService {

    List<FullAppointmentDTO> listAllAppointments();
    List<FullAppointmentDTO> listAppointmentsByCustomer(Long customerId);
    List<FullAppointmentDTO> listAllAppointmentByBeautyProcedureId(Long beautyProcedureId);

}
