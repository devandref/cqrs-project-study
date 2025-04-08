package io.github.devandref.beautique.controller;

import io.github.devandref.beautique.dto.BeautyProcedureDTO;
import io.github.devandref.beautique.service.BeautyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("beauty-procedures")
public class BeautyProcedureController {

    @Autowired
    private BeautyProcedureService beautyProcedureService;

    @PostMapping
    ResponseEntity<BeautyProcedureDTO> create(@RequestBody BeautyProcedureDTO beautyProcedureDTO) {
        return ResponseEntity.ok(beautyProcedureService.create(beautyProcedureDTO));
    }

}
