package io.github.devandref.beautique.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.devandref.beautique.dto.CustomerDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String phone;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentsEntity> appointments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<AppointmentsEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentsEntity> appointments) {
        this.appointments = appointments;
    }

    public CustomerEntity dtoToEntity(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerDTO.getName());
        customerEntity.setPhone(customerDTO.getPhone());
        customerEntity.setEmail(customerDTO.getEmail());
        return customerEntity;
    }
}
