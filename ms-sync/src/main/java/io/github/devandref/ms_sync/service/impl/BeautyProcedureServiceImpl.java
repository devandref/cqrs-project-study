package io.github.devandref.ms_sync.service.impl;

import io.github.devandref.ms_sync.dtos.BeautyProcedure.BeautyProcedureDTO;
import io.github.devandref.ms_sync.repositories.BeautyProcedureRepository;
import io.github.devandref.ms_sync.service.BeautyProcedureService;
import io.github.devandref.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Override
    public void saveBeautyProcedure(BeautyProcedureDTO beautyProcedure) {
        try {
            SyncLogger.info("Saving beauty procedure: " + beautyProcedure.getId());
            beautyProcedureRepository.save(beautyProcedure);
        } catch (Exception ex) {
            SyncLogger.error("Error saving beauty procedure: " + ex.getMessage());
            SyncLogger.trace(Arrays.toString(ex.getStackTrace()));
        }
    }


}
