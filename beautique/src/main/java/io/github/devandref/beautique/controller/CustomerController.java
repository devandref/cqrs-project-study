package io.github.devandref.beautique.controller;

import io.github.devandref.beautique.dto.CustomerDTO;
import io.github.devandref.beautique.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        var response = customerService.create(customerDTO);
        return ResponseEntity.ok(response);
    }

}
