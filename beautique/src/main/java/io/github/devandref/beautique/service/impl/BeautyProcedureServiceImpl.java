package io.github.devandref.beautique.service.impl;

import io.github.devandref.beautique.component.ConverterUtilComponent;
import io.github.devandref.beautique.dto.BeautyProcedureDTO;
import io.github.devandref.beautique.entities.BeautyProceduresEntity;
import io.github.devandref.beautique.repository.BeautyProcedureRepository;
import io.github.devandref.beautique.service.BeautyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    private final ConverterUtilComponent<BeautyProceduresEntity, BeautyProcedureDTO> converterUtil = new ConverterUtilComponent<>(BeautyProceduresEntity.class, BeautyProcedureDTO.class);

    @Override
    public BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO) {
        BeautyProceduresEntity beautyProceduresEntity = converterUtil.convertToSource(beautyProcedureDTO);
        BeautyProceduresEntity newBeautyProceduresEntity = beautyProcedureRepository.save(beautyProceduresEntity);
        return converterUtil.convertToTarget(newBeautyProceduresEntity);
    }
}
