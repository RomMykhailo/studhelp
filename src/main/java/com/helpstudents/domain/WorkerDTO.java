package com.helpstudents.domain;

import com.helpstudents.entity.RoleEntity;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkerDTO {

    @NotNull(message = "Field 'Name' can not be null")
    @Size(min=2, max=20, message = "Not valid length 'Name'")
    private String nickName;
    private String description;
    @NotNull(message = "Field 'Email' can not be null")
    @Size(min=5, max=100, message = "Not valid length 'email'")
    private String email;
    @NotNull(message = "Field 'password' can not be null")
    @Size(min=4, max=40, message = "Not valid length 'password'")
    private String password;
    @NotNull(message = "Field 'passwordConfirm' can not be null")
    @Size(min=4, max=40, message = "Not valid length 'password'")
    private String passwordConfirm;
    private String phoneNumber;

}
