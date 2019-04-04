package com.helpstudents.service;

import com.helpstudents.domain.WorkerDTO;
import com.helpstudents.domain.WorkerResponseDTO;
import com.helpstudents.entity.WorkerEntity;

import java.util.List;

public interface WorkerService {

   WorkerDTO getWorkerById (Long id);

    List<WorkerResponseDTO> getAllNotActivateWorker();
    List<WorkerResponseDTO> getAllWorker();
    WorkerResponseDTO findByNickName (String nickName);
    void activateWorker (Long id);
    void deactivateWorker (Long id);
    WorkerResponseDTO getWorkerByToken (String token);

}
