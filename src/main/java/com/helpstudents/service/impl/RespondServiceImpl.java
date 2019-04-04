package com.helpstudents.service.impl;

import com.helpstudents.domain.RespondDTO;
import com.helpstudents.entity.RespondEntity;
import com.helpstudents.repository.RespondRepository;
import com.helpstudents.service.RespondService;
import com.helpstudents.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespondServiceImpl implements RespondService {
    @Autowired
    private ObjectMapperUtils objectMapperUtils;
    @Autowired
    private RespondRepository respondRepository;

    @Override
    public RespondDTO createRespond(RespondDTO respondDTO) {
        RespondEntity respondEntity = objectMapperUtils.map(respondDTO, RespondEntity.class);
        respondRepository.save(respondEntity);
        return respondDTO;
    }

    @Override
    public List<RespondDTO> getAllRespondByWorkerId(Long id) {
        List<RespondEntity> respondEntities =respondRepository.findAllByWorkerEntityId(id);
        List<RespondDTO> respondDTOS =objectMapperUtils.mapAll(respondEntities,RespondDTO.class);
        return respondDTOS;
    }

    @Override
    public List<RespondDTO> getAllRespondByCustomerId(Long id) {
        List<RespondEntity> respondEntities =respondRepository.findAllByCustomerEntityId(id);
        List<RespondDTO> respondDTOS =objectMapperUtils.mapAll(respondEntities,RespondDTO.class);
        return respondDTOS;
    }
}
