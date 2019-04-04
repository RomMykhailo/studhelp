package com.helpstudents.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AdminDTO {

    @NotNull(message = "Field 'Email' can not be null")
    @Size(min=5, max=100, message = "Not valid length 'email'")
    private String email;
    @NotNull(message = "Field 'password' can not be null")
    @Size(min=4, max=40, message = "Not valid length 'password'")
    private String password;
    private String passwordConfirm;
    @Size(min=2, max = 16, message = "Not valid length 'firstname'")
    private String firstName;
    @Size(min=2, max = 16, message = "Not valid length 'lastname'")

    private String lastName;

    private String phoneNumber;
}
