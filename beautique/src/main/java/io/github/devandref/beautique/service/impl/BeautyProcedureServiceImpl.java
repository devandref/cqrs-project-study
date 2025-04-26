package io.github.devandref.beautique.service.impl;

import io.github.devandref.beautique.component.ConverterUtilComponent;
import io.github.devandref.beautique.dto.BeautyProcedureDTO;
import io.github.devandref.beautique.entities.BeautyProceduresEntity;
import io.github.devandref.beautique.repository.BeautyProcedureRepository;
import io.github.devandref.beautique.service.BeautyProcedureService;
import io.github.devandref.beautique.service.BrokerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    private static String NOT_FOUND_ERROR_MSG = "Beauty Procedure Not Found";

    private final BrokerService brokerService;
    private final BeautyProcedureRepository beautyProcedureRepository;

    private final ConverterUtilComponent<BeautyProceduresEntity, BeautyProcedureDTO> converterUtil = new ConverterUtilComponent<>(BeautyProceduresEntity.class, BeautyProcedureDTO.class);

    public BeautyProcedureServiceImpl(BeautyProcedureRepository beautyProcedureRepository, BrokerService brokerService) {
        this.beautyProcedureRepository = beautyProcedureRepository;
        this.brokerService = brokerService;
    }

    @Override
    public BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO) {
        BeautyProceduresEntity beautyProceduresEntity = converterUtil.convertToSource(beautyProcedureDTO);
        BeautyProceduresEntity newBeautyProceduresEntity = beautyProcedureRepository.save(beautyProceduresEntity);
        sendBeautyProceduresToQueue(beautyProceduresEntity);
        return converterUtil.convertToTarget(newBeautyProceduresEntity);
    }

    @Override
    public void delete(Long id) {
        Optional<BeautyProceduresEntity> beautyProceduresEntityOptional = beautyProcedureRepository.findById(id);
        if (beautyProceduresEntityOptional.isEmpty()) {
            throw new RuntimeException(NOT_FOUND_ERROR_MSG);
        }
        beautyProcedureRepository.deleteById(id);
    }

    @Override
    public BeautyProcedureDTO update(BeautyProcedureDTO beautyProcedureDTO) {
        Optional<BeautyProceduresEntity> beautyProceduresEntityOptional = beautyProcedureRepository.findById(beautyProcedureDTO.getId());
        if (beautyProceduresEntityOptional.isEmpty()) {
            throw new RuntimeException(NOT_FOUND_ERROR_MSG);
        }
        BeautyProceduresEntity beautyProceduresEntity = converterUtil.convertToSource(beautyProcedureDTO);
        beautyProceduresEntity.setAppointments(beautyProceduresEntityOptional.get().getAppointments());
        beautyProceduresEntity.setCreatedAt(beautyProceduresEntityOptional.get().getCreatedAt());
        BeautyProceduresEntity updateBeautyProceduresEntity = beautyProcedureRepository.save(beautyProceduresEntity);

        this.sendBeautyProceduresToQueue(updateBeautyProceduresEntity);
        return converterUtil.convertToTarget(updateBeautyProceduresEntity);
    }

    private void sendBeautyProceduresToQueue(BeautyProceduresEntity beautyProceduresEntity) {
        BeautyProcedureDTO beautyProcedureDTO = BeautyProcedureDTO.builder()
                .id(beautyProceduresEntity.getId())
                .name(beautyProceduresEntity.getName())
                .description(beautyProceduresEntity.getDescription())
                .price(beautyProceduresEntity.getPrice())
                .build();
        brokerService.send("beautyProcedure", beautyProcedureDTO);
    }


}
