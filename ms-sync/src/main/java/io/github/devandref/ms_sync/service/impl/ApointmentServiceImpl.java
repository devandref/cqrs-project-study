package io.github.devandref.ms_sync.service.impl;

import io.github.devandref.ms_sync.dtos.BeautyProcedure.BeautyProcedureDTO;
import io.github.devandref.ms_sync.dtos.appointments.FullAppointmentDTO;
import io.github.devandref.ms_sync.dtos.customers.CustomerDTO;
import io.github.devandref.ms_sync.repositories.AppointmentRepository;
import io.github.devandref.ms_sync.service.AppointmentService;
import io.github.devandref.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ApointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveAppointment(FullAppointmentDTO appointment) {
        try {
            SyncLogger.info("Saving appointment: " + appointment.getId());
            appointmentRepository.save(appointment);
        } catch (Exception ex) {
            SyncLogger.error("Error saving appointment: " + ex.getMessage());
            SyncLogger.trace(Arrays.toString(ex.getStackTrace()));
        }
    }

    @Override
    public void updateAppointmentCustomer(CustomerDTO customer) {
        try {
            SyncLogger.trace("updating appointment customer: " + customer.getId());
            Query query = new Query(Criteria.where("customer_id").is(customer.getId()));
            Update update = new Update().set("customer", customer);
            mongoTemplate.updateMulti(query, update, FullAppointmentDTO.class);
        } catch (Exception e) {
            SyncLogger.error("Error updating appointment customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateAppointmentBeautyProcedures(BeautyProcedureDTO beautyProcedureDTO) {
        try {
            SyncLogger.info("Updating appointment beauty procedure: " + beautyProcedureDTO.getId());
            Query query = new Query(Criteria.where("beautyProcedure.id").is(beautyProcedureDTO.getId()));
            Update update = new Update()
                    .set("beautyProcedure.name", beautyProcedureDTO.getName())
                    .set("beautyProcedure.description", beautyProcedureDTO.getDescription());
            mongoTemplate.updateMulti(query, update, FullAppointmentDTO.class);
        } catch (Exception e) {
            SyncLogger.error("Error updating appointment beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
