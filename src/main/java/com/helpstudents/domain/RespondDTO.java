package com.helpstudents.domain;

import lombok.Data;

@Data
public class RespondDTO {

    private String description;

    private WorkerDTOForAll workerDTOForAll;

    private CustomerDTOForAll customerDTOForAll;

    private boolean isDeleted;
}
