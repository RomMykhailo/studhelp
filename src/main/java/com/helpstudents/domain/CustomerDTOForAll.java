package com.helpstudents.domain;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class CustomerDTOForAll {

    private String nickName;
    private LocalDateTime dateCreate;
}
