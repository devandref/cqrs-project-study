package io.github.devandref.beautique.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointments")
public class AppointmentsEntity extends BaseEntity{

    @Column(nullable = false, updatable = true)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private Boolean appointmentsOpen;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beauty_procedure_id", nullable = false)
    private BeautyProceduresEntity beautyProcedure;


}
