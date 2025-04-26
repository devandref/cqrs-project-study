package io.github.devandref.ms_sync.dtos.BeautyProcedure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "beautyprocedures")
public class BeautyProcedureDTO {

    private Long id;
    private String name, description;
    private BigDecimal price;
}
