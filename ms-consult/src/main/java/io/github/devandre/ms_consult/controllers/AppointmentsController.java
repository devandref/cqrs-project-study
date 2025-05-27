package io.github.devandre.ms_consult.controllers;

import io.github.devandre.ms_consult.dtos.appointments.FullAppointmentDTO;
import io.github.devandre.ms_consult.services.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {

    private final AppointmentService appointmentService;

    public AppointmentsController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping()
    ResponseEntity<List<FullAppointmentDTO>> listAllBeautyProcedures() {
        return ResponseEntity.ok(appointmentService.listAllAppointments());
    }

    @GetMapping("/customer/{customer}")
    ResponseEntity<List<FullAppointmentDTO>> listByNameLikeIgnoreCase(@PathVariable Long customer) {
        return ResponseEntity.ok(appointmentService.listAppointmentsByCustomer(customer));
    }

    @GetMapping("/description/{description}")
    ResponseEntity<List<FullAppointmentDTO>> listByDescriptionIgnoreCase(@PathVariable Long description) {
        return ResponseEntity.ok(appointmentService.listAllAppointmentByBeautyProcedureId(description));
    }
}
