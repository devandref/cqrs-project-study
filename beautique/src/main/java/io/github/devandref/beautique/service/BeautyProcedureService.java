package io.github.devandref.beautique.service;

import io.github.devandref.beautique.dto.BeautyProcedureDTO;

public interface BeautyProcedureService {

    BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO);
    void delete(Long id);
    BeautyProcedureDTO update(BeautyProcedureDTO beautyProcedureDTO);

}
