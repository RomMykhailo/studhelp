package com.helpstudents.service;

import com.helpstudents.domain.RespondDTO;

import java.util.List;

public interface RespondService {
    RespondDTO createRespond (RespondDTO respondDTO);
    List<RespondDTO> getAllRespondByWorkerId (Long id);
    List<RespondDTO> getAllRespondByCustomerId (Long id);
}
