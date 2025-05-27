package io.github.devandre.ms_consult.dtos.beautyprocedures;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "beautyprocedures")
public class BeautyProcedureDTO {

    private Long id;
    private String name, description;
    private BigDecimal price;


}
