package com.helpstudents.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class WorkerDTOForAll {
    private Long id;
    @NotNull(message = "Field 'Name' can not be null")
    @Size(min=2, max=20, message = "Not valid length 'Name'")
    private String nickName;
    private String description;
}
