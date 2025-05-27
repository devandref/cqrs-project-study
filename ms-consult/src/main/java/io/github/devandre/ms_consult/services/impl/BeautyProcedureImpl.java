package io.github.devandre.ms_consult.services.impl;

import io.github.devandre.ms_consult.dtos.beautyprocedures.BeautyProcedureDTO;
import io.github.devandre.ms_consult.repositories.BeautyProcedureRepository;
import io.github.devandre.ms_consult.services.BeautyProcedureService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeautyProcedureImpl implements BeautyProcedureService {

    private final BeautyProcedureRepository beautyProcedureRepository;

    public BeautyProcedureImpl(BeautyProcedureRepository beautyProcedureRepository) {
        this.beautyProcedureRepository = beautyProcedureRepository;
    }

    @Override
    public List<BeautyProcedureDTO> listAllBeautyProcedure() {
        try {
            return beautyProcedureRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error listening all beauty procedures.");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByNameIgnoreCase(String name) {
        try {
            return beautyProcedureRepository.findByNameIgnoreCase(name);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error listing all beauty procedure by name");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByDescriptionIgnoreCase(String description) {
        try {
            return beautyProcedureRepository.findByDescriptionIgnoreCase(description);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error listing all beauty procedure by description");
        }
    }
}
