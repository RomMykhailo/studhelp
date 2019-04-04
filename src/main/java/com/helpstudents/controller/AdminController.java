package com.helpstudents.controller;

import com.helpstudents.config.AppConstants;
import com.helpstudents.domain.AdminResponseDTO;
import com.helpstudents.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getByToken")
    public ResponseEntity<?> getAdminByToken (@RequestHeader(value = "Authorization") String token){
        String token2 = token.replace(AppConstants.TOKEN_PREFIX,"");
        AdminResponseDTO adminResponseDTO = adminService.getAdminByToken(token2);
        return  new ResponseEntity<>(adminResponseDTO,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_WORKER', 'ROLE_CUSTOMER')")
    @GetMapping("/getAll")
    public ResponseEntity getAllAdmins (){
        List<AdminResponseDTO> adminResponseDTOS = adminService.getAllAdmins();
        return new ResponseEntity(adminResponseDTOS, HttpStatus.OK);
    }
}
