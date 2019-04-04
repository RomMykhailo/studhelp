package com.helpstudents.controller;

import com.helpstudents.domain.RespondDTO;
import com.helpstudents.domain.WorkerDTO;
import com.helpstudents.service.RespondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("respond")
public class RespondController {
    @Autowired
    private RespondService respondService;

    @PostMapping("/add")
    public ResponseEntity<?> createRespond(@Valid @RequestBody RespondDTO respondDTO){
        respondService.createRespond(respondDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/worker/id/{id}")
    public ResponseEntity<?> getAllRespondByWorkerId (@PathVariable Long id){
        List<RespondDTO> respondDTOS = respondService.getAllRespondByWorkerId(id);
        return new ResponseEntity(respondDTOS,HttpStatus.OK);
    }

}
