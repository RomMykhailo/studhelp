package com.helpstudents.service;

import com.helpstudents.domain.AdminResponseDTO;

import java.util.List;

public interface AdminService {
    AdminResponseDTO getAdminByToken (String token);
    List<AdminResponseDTO> getAllAdmins ();
}
