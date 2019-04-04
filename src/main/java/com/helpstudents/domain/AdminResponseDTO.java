package com.helpstudents.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminResponseDTO {
    private Long id;
    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;
}
