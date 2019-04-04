package com.helpstudents.service.impl;

import com.helpstudents.config.jwt.JvtTokenProvider;
import com.helpstudents.domain.AdminResponseDTO;
import com.helpstudents.domain.WorkerDTO;
import com.helpstudents.domain.WorkerResponseDTO;
import com.helpstudents.entity.AdminEntity;
import com.helpstudents.entity.RoleEntity;
import com.helpstudents.entity.WorkerEntity;
import com.helpstudents.exeptions.ExistsExceptions;
import com.helpstudents.repository.RoleRepository;
import com.helpstudents.repository.WorkerRepository;
import com.helpstudents.service.WorkerService;
import com.helpstudents.utils.ObjectMapperUtils;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private ObjectMapperUtils objectMapperUtils;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JvtTokenProvider jvtTokenProvider;

    private boolean existsWorker (Long id){

        return workerRepository.existsById(id);
    }

    @Override
    public WorkerDTO getWorkerById(Long id) {
       if(!existsWorker(id)){
            throw new ExistsExceptions("Worker with id " + id + " not exists");
        }
       WorkerEntity workerEntity = workerRepository.findById(id).get();
       WorkerDTO workerDTO = objectMapperUtils.map(workerEntity, WorkerDTO.class);
        return workerDTO;
    }

    @Override
    public List<WorkerResponseDTO> getAllNotActivateWorker() {
        List<WorkerEntity> workers = workerRepository.findAllByRolesNull();
        List<WorkerResponseDTO> workerDTOS = objectMapperUtils.mapAll(workers, WorkerResponseDTO.class);
        return workerDTOS;
    }

    @Override
    public List<WorkerResponseDTO> getAllWorker() {
        List<WorkerEntity> workers = workerRepository.findAll();
        List<WorkerResponseDTO> workerDTOS = objectMapperUtils.mapAll(workers, WorkerResponseDTO.class);
//        workers.forEach(e->{WorkerDTO workerDTO = objectMapperUtils.map(e, WorkerDTO.class);
//            WorkerDTO workerDTO = new WorkerDTO();
//            workerDTO.setId(e.getId());
//            workerDTO.setName(e.getName());
//            workerDTO.setEmail(e.getEmail());
//            workerDTO.setDescription(e.getDescription());
//            workerDTOS.add(workerDTO);
//        });
        return workerDTOS;
    }

    @Override
    public WorkerResponseDTO findByNickName(String nickName) {
        WorkerResponseDTO workerDTO = objectMapperUtils.map(workerRepository.findByNickName(nickName),WorkerResponseDTO.class);
        return workerDTO;
    }

    @Override
    public void activateWorker(Long id) {
        if(!existsWorker(id)){
            throw new ExistsExceptions("Worker with id " + id + " not exists");
        }
        WorkerEntity workerEntity = workerRepository.findById(id).get();
        List<RoleEntity> rols = new ArrayList<>();
        rols.add(roleRepository.findByRole("WORKER").get());
        workerEntity.setRoles(rols);
        workerRepository.save(workerEntity);
    }
    @Override
    public void deactivateWorker(Long id) {
        if(!existsWorker(id)){
            throw new ExistsExceptions("Worker with id " + id + " not exists");
        }
        WorkerEntity workerEntity = workerRepository.findById(id).get();
        workerEntity.setRoles(null);
        workerRepository.save(workerEntity);
    }

    @Override
    public WorkerResponseDTO getWorkerByToken(String token) {
        WorkerResponseDTO workerResponseDTO;
        String email = jvtTokenProvider.getUsernameFromToken(token);
        if(workerRepository.existsByEmailIgnoreCase(email)){
            WorkerEntity workerEntity = workerRepository.findByEmailIgnoreCase(email).get();
            workerResponseDTO = objectMapperUtils.map(workerEntity, WorkerResponseDTO.class);
        }else{
            throw new ExistsExceptions("Admin with email " + email + " not exists");
        }
        return workerResponseDTO;
    }
}
