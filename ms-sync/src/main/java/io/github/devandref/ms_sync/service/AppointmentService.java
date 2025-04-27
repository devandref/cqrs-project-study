package io.github.devandref.ms_sync.service;

import io.github.devandref.ms_sync.dtos.BeautyProcedure.BeautyProcedureDTO;
import io.github.devandref.ms_sync.dtos.appointments.FullAppointmentDTO;
import io.github.devandref.ms_sync.dtos.customers.CustomerDTO;

public interface AppointmentService {
    void saveAppointment(FullAppointmentDTO appointment);
    void updateAppointmentCustomer(CustomerDTO customer);
    void updateAppointmentBeautyProcedures(BeautyProcedureDTO beautyProcedureDTO);
}
