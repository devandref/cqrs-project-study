package io.github.devandref.ms_sync.listeners.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.devandref.ms_sync.dtos.BeautyProcedure.BeautyProcedureDTO;
import io.github.devandref.ms_sync.dtos.appointments.FullAppointmentDTO;
import io.github.devandref.ms_sync.dtos.customers.CustomerDTO;
import io.github.devandref.ms_sync.listeners.ListenerConfig;
import io.github.devandref.ms_sync.service.SyncService;
import io.github.devandref.ms_sync.utils.SyncLogger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQListenerConfig implements ListenerConfig {

    private final ObjectMapper objectMapper;
    private final SyncService syncService;

    public RabbitMQListenerConfig(ObjectMapper objectMapper, SyncService syncService) {
        this.objectMapper = objectMapper;
        this.syncService = syncService;
    }

    @Override
    @RabbitListener(queues = "customerQueue")
    public void listenToCustomerQueue(String message) {
        try {
            CustomerDTO customerDTO = objectMapper.readValue(message, CustomerDTO.class);
            syncService.syncCustomer(customerDTO);
            SyncLogger.info("Message received from queue customerQueue: " + customerDTO.toString());
        } catch (Exception e) {
            SyncLogger.error("Error listen customer queue: " + e.getMessage());
        }
    }

    @Override
    @RabbitListener(queues = "appointmentQueue")
    public void listenToAppointmentQueue(String message) {
        try {
            FullAppointmentDTO fullAppointmentDTO = objectMapper.readValue(message, FullAppointmentDTO.class);
            syncService.syncAppointment(fullAppointmentDTO);
            SyncLogger.info("Message received from queue AppointmentQueue: " + fullAppointmentDTO.toString());
        } catch (Exception e) {
            SyncLogger.error("Error listen AppointmentQueue: " + e.getMessage());
        }
    }

    @Override
    @RabbitListener(queues = "beautyProcedureQueue")
    public void listenToBeautyProcedureQueue(String message) {
        try {
            BeautyProcedureDTO beautyProcedureDTO = objectMapper.readValue(message, BeautyProcedureDTO.class);
            syncService.syncBeautyProcedures(beautyProcedureDTO);
            SyncLogger.info("Message received from queue beautyProcedureQueue: " + beautyProcedureDTO.toString());
        } catch (Exception e) {
            SyncLogger.error("Error listen beautyProcedureQueue: " + e.getMessage());
        }
    }

}
