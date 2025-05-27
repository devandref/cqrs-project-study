package io.github.devandref.ms_sync.service.impl;

import io.github.devandref.ms_sync.dtos.BeautyProcedure.BeautyProcedureDTO;
import io.github.devandref.ms_sync.dtos.appointments.FullAppointmentDTO;
import io.github.devandref.ms_sync.dtos.customers.CustomerDTO;
import io.github.devandref.ms_sync.service.AppointmentService;
import io.github.devandref.ms_sync.service.BeautyProcedureService;
import io.github.devandref.ms_sync.service.CustomerService;
import io.github.devandref.ms_sync.service.SyncService;
import io.github.devandref.ms_sync.utils.SyncLogger;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SyncServiceImpl implements SyncService {

    private final AppointmentService appointmentService;
    private final CustomerService customerService;
    private final BeautyProcedureService beautyProcedureService;

    public SyncServiceImpl(AppointmentService appointmentService, CustomerService customerService, BeautyProcedureService beautyProcedureService) {
        this.appointmentService = appointmentService;
        this.customerService = customerService;
        this.beautyProcedureService = beautyProcedureService;
    }

    @Override
    public void syncCustomer(CustomerDTO customer) {
        try {
            SyncLogger.info("Saving customer: " + customer.getId());
            customerService.saveCustomer(customer);
            SyncLogger.info("Updating appointment customer: " + customer.getId());
            appointmentService.updateAppointmentCustomer(customer);
        } catch (Exception e) {
            SyncLogger.error("Error saving customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncAppointment(FullAppointmentDTO appointment) {
        try {
            SyncLogger.info("Saving appointment: " + appointment.getId());
            appointmentService.saveAppointment(appointment);
        } catch (Exception ex) {
            SyncLogger.error("Error saving appointment: " + ex.getMessage());
            SyncLogger.trace(Arrays.toString(ex.getStackTrace()));
        }
    }

    @Override
    public void syncBeautyProcedures(BeautyProcedureDTO beautyProcedureDTO) {
        try {
            SyncLogger.info("Saving beauty procedure: " + beautyProcedureDTO.getId());
            beautyProcedureService.saveBeautyProcedure(beautyProcedureDTO);
            SyncLogger.info("Updating appointment beuaty procedure: " + beautyProcedureDTO.getId());
            appointmentService.updateAppointmentBeautyProcedures(beautyProcedureDTO);
        } catch (Exception e) {
            SyncLogger.error("Error saving beuaty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
