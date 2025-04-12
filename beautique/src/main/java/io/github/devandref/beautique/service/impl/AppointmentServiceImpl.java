package io.github.devandref.beautique.service.impl;

import io.github.devandref.beautique.component.ConverterUtilComponent;
import io.github.devandref.beautique.dto.AppointmentDTO;
import io.github.devandref.beautique.entities.AppointmentsEntity;
import io.github.devandref.beautique.entities.BeautyProceduresEntity;
import io.github.devandref.beautique.entities.CustomerEntity;
import io.github.devandref.beautique.repository.AppointmentRepository;
import io.github.devandref.beautique.repository.BeautyProcedureRepository;
import io.github.devandref.beautique.repository.CustomerRepository;
import io.github.devandref.beautique.service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentsService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private final ConverterUtilComponent<AppointmentsEntity, AppointmentDTO> converterUtil = new ConverterUtilComponent<>(AppointmentsEntity.class, AppointmentDTO.class);

    private final String APPOINTMENT_NOT_FOUND_ERROR_MENSAGE = "Appointment not found.";
    private final String CUSTOMER_NOT_FOUND_ERROR_MENSAGE = "Customer not found.";
    private final String BEAUTY_PROCEDURE_NOT_FOUND_ERROR_MENSAGE = "Beauty Procedure not found.";

    @Override
    public AppointmentDTO create(AppointmentDTO appointmentDTO) {
        AppointmentsEntity appointmentsEntity = converterUtil.convertToSource(appointmentDTO);
        AppointmentsEntity newAppointmentsEntity = appointmentRepository.save(appointmentsEntity);
        return converterUtil.convertToTarget(newAppointmentsEntity);
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointmentDTO) {
        Optional<AppointmentsEntity> currentAppointment = appointmentRepository.findById(appointmentDTO.getId());
        if (currentAppointment.isEmpty()) {
            throw new RuntimeException(APPOINTMENT_NOT_FOUND_ERROR_MENSAGE);
        }
        AppointmentsEntity appointmentsEntity = converterUtil.convertToSource(appointmentDTO);
        appointmentsEntity.setCreatedAt(currentAppointment.get().getCreatedAt());
        AppointmentsEntity updatedAppointmentEntity = appointmentRepository.save(appointmentsEntity);
        return converterUtil.convertToTarget(updatedAppointmentEntity);
    }

    @Override
    public void deleteById(Long id) {
        AppointmentsEntity appointmentsEntity = appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException(APPOINTMENT_NOT_FOUND_ERROR_MENSAGE));
        appointmentRepository.delete(appointmentsEntity);
    }

    @Override
    public AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentDTO) {
        CustomerEntity customerEntity = findCustomerById(appointmentDTO.getCustomer());
        BeautyProceduresEntity beautyProcedureById = findBeautyProcedureById(appointmentDTO.getBeautyProcedure());
        AppointmentsEntity appointmentById = findAppointmentById(appointmentDTO.getId());
        appointmentById.setCustomer(customerEntity);
        appointmentById.setBeautyProcedure(beautyProcedureById);
        appointmentById.setAppointmentsOpen(false);

        AppointmentsEntity appointmentsEntity = appointmentRepository.save(appointmentById);
        return buildAppointmentsDTO(appointmentsEntity);
    }

    private AppointmentsEntity findAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException(APPOINTMENT_NOT_FOUND_ERROR_MENSAGE));
    }

    private BeautyProceduresEntity findBeautyProcedureById(Long id) {
        return beautyProcedureRepository.findById(id).orElseThrow(() -> new RuntimeException(BEAUTY_PROCEDURE_NOT_FOUND_ERROR_MENSAGE));
    }

    private CustomerEntity findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException(CUSTOMER_NOT_FOUND_ERROR_MENSAGE));
    }

    private AppointmentDTO buildAppointmentsDTO(AppointmentsEntity appointmentsEntity) {
        return AppointmentDTO.builder()
                .id(appointmentsEntity.getId())
                .beautyProcedure(appointmentsEntity.getBeautyProcedure().getId())
                .dateTime(appointmentsEntity.getDateTime())
                .appointmentsOpen(appointmentsEntity.getAppointmentsOpen())
                .customer(appointmentsEntity.getCustomer().getId())
                .build();
    }

}
