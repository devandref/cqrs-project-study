package io.github.devandref.ms_sync.service;

import io.github.devandref.ms_sync.dtos.BeautyProcedure.BeautyProcedureDTO;
import io.github.devandref.ms_sync.dtos.appointments.FullAppointmentDTO;
import io.github.devandref.ms_sync.dtos.customers.CustomerDTO;

public interface SyncService {
    void syncCustomer(CustomerDTO customer);
    void syncAppointment(FullAppointmentDTO fullAppointment);
    void syncBeautyProcedures(BeautyProcedureDTO beautyProcedureDTO);
}
