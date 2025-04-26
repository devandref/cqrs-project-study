package io.github.devandref.beautique.service.impl;

import io.github.devandref.beautique.component.ConverterUtilComponent;
import io.github.devandref.beautique.dto.AppointmentDTO;
import io.github.devandref.beautique.dto.BeautyProcedureDTO;
import io.github.devandref.beautique.dto.CustomerDTO;
import io.github.devandref.beautique.dto.FullAppointmentDTO;
import io.github.devandref.beautique.entities.AppointmentsEntity;
import io.github.devandref.beautique.entities.BeautyProceduresEntity;
import io.github.devandref.beautique.entities.CustomerEntity;
import io.github.devandref.beautique.repository.AppointmentRepository;
import io.github.devandref.beautique.repository.BeautyProcedureRepository;
import io.github.devandref.beautique.repository.CustomerRepository;
import io.github.devandref.beautique.service.AppointmentsService;
import io.github.devandref.beautique.service.BrokerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentsService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BrokerService brokerService;

    private final ConverterUtilComponent<AppointmentsEntity, AppointmentDTO> converterUtil = new ConverterUtilComponent<>(AppointmentsEntity.class, AppointmentDTO.class);

    private final String APPOINTMENT_NOT_FOUND_ERROR_MENSAGE = "Appointment not found.";
    private final String CUSTOMER_NOT_FOUND_ERROR_MENSAGE = "Customer not found.";
    private final String BEAUTY_PROCEDURE_NOT_FOUND_ERROR_MENSAGE = "Beauty Procedure not found.";

    @Override
    public AppointmentDTO create(AppointmentDTO appointmentDTO) {
        AppointmentsEntity appointmentsEntity = converterUtil.convertToSource(appointmentDTO);
        AppointmentsEntity newAppointmentsEntity = appointmentRepository.save(appointmentsEntity);
        sendAppointmentToQueue(newAppointmentsEntity);
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
        sendAppointmentToQueue(updatedAppointmentEntity);
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
        sendAppointmentToQueue(appointmentsEntity);
        return buildAppointmentsDTO(appointmentsEntity);
    }

    private void sendAppointmentToQueue(AppointmentsEntity appointmentsEntity) {
        CustomerDTO customerDTO = appointmentsEntity.getCustomer() != null ? modelMapper.map(appointmentsEntity.getCustomer(), CustomerDTO.class) : null;
        BeautyProcedureDTO beautyProcedureDTO = appointmentsEntity.getBeautyProcedure() != null ? modelMapper.map(appointmentsEntity.getBeautyProcedure(), BeautyProcedureDTO.class) : null;
        FullAppointmentDTO fullAppointmentDTO = FullAppointmentDTO.builder()
                .id(appointmentsEntity.getId())
                .dateTime(appointmentsEntity.getDateTime())
                .appointmentsOpen(appointmentsEntity.getAppointmentsOpen())
                .customer(customerDTO)
                .beautyProcedure(beautyProcedureDTO)
                .build();
        brokerService.send("appointments", fullAppointmentDTO);
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
