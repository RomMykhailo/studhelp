package com.helpstudents.domain;

import com.helpstudents.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerResponseDTO extends WorkerDTOForAll {
    private String email;
    private String phoneNumber;
    private List<RoleEntity> roles;
}
