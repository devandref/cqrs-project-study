package io.github.devandre.ms_consult.dtos.appointments;

import io.github.devandre.ms_consult.dtos.beautyprocedures.BeautyProcedureDTO;
import io.github.devandre.ms_consult.dtos.customers.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "appointments")
public class FullAppointmentDTO {

    private Long id;
    private LocalDateTime dateTime;
    private Boolean appointmentOpen;

    private CustomerDTO customer;
    private BeautyProcedureDTO beautyProcedure;

}
