package com.helpstudents.domain;

import com.helpstudents.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTOForAdmin extends CustomerDTOForAll {
    private Long id;
    private String email;
    private String phoneNumber;
    private RoleEntity role;
}
