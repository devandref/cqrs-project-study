package io.github.devandref.ms_sync.dtos.appointments;

import io.github.devandref.ms_sync.dtos.BeautyProcedure.BeautyProcedureDTO;
import io.github.devandref.ms_sync.dtos.customers.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "appointments")
public class FullAppointmentDTO {

    private Long id;
    private LocalDateTime dateTime;
    private Boolean appointmentsOpen;
    private CustomerDTO customer;
    private BeautyProcedureDTO beautyProcedure;


}
