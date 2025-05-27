package io.github.devandre.ms_consult.services.impl;

import io.github.devandre.ms_consult.dtos.appointments.FullAppointmentDTO;
import io.github.devandre.ms_consult.repositories.AppointmentRepository;
import io.github.devandre.ms_consult.services.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<FullAppointmentDTO> listAllAppointments() {
        try {
            return appointmentRepository.findAll();
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error listing all appointments");
        }
    }

    @Override
    public List<FullAppointmentDTO> listAppointmentsByCustomer(Long customerId) {
        try {
            return appointmentRepository.listAppointmentsByCustomerId(customerId);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error listing all appointments by customer id");
        }
    }

    @Override
    public List<FullAppointmentDTO> listAllAppointmentByBeautyProcedureId(Long beautyProcedureId) {
        try {
            return appointmentRepository.listAppointmentsByBeautyProcedureId(beautyProcedureId);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error listing all appointments by beauty procedure id");
        }
    }
}
