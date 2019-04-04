package com.helpstudents.service.impl;

import com.helpstudents.config.jwt.JvtTokenProvider;
import com.helpstudents.domain.AdminResponseDTO;
import com.helpstudents.entity.AdminEntity;
import com.helpstudents.exeptions.ExistsExceptions;
import com.helpstudents.repository.AdminRepository;
import com.helpstudents.service.AdminService;
import com.helpstudents.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private JvtTokenProvider jvtTokenProvider;
    @Autowired
    private ObjectMapperUtils objectMapperUtils;

    @Override
    public AdminResponseDTO getAdminByToken(String token) {
        AdminResponseDTO adminResponseDTO;
       String email = jvtTokenProvider.getUsernameFromToken(token);
        if(adminRepository.existsByEmailIgnoreCase(email)){
            AdminEntity adminEntity = adminRepository.findByEmailIgnoreCase(email).get();
            adminResponseDTO = objectMapperUtils.map(adminEntity, AdminResponseDTO.class);
        }else{
            throw new ExistsExceptions("Admin with email " + email + " not exists");
        }
        return adminResponseDTO;
    }

    @Override
    public List<AdminResponseDTO> getAllAdmins() {
        List<AdminEntity> adminEntities = adminRepository.findAll();
        List<AdminResponseDTO> adminResponseDTOS = objectMapperUtils.mapAll(adminEntities,AdminResponseDTO.class);
        return adminResponseDTOS;
    }
}
