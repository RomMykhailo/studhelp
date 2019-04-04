package com.helpstudents.controller;

import com.helpstudents.config.AppConstants;
import com.helpstudents.domain.AdminResponseDTO;
import com.helpstudents.domain.WorkerDTO;
import com.helpstudents.domain.WorkerResponseDTO;
import com.helpstudents.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import javax.validation.Valid;

@RestController
@RequestMapping("worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/activate/{id}")
    public ResponseEntity<?> activateWorker(@PathVariable Long id){
        workerService.activateWorker(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateWorker(@PathVariable Long id){
        workerService.deactivateWorker(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_WORKER')")
    @GetMapping("id/{id}")
    public ResponseEntity<?> getWorkerById (@PathVariable Long id){
        WorkerDTO workerDTO = workerService.getWorkerById(id);
        return new ResponseEntity(workerDTO,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllForAdmin")
    public ResponseEntity<?> getAllWorkers (){
        return  new ResponseEntity<>(workerService.getAllWorker(),HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllNotActive")
    public ResponseEntity<?> getAllNotActivateWorkers (){
        return  new ResponseEntity<>(workerService.getAllNotActivateWorker(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_WORKER')")
    @GetMapping("/getByToken")
    public ResponseEntity<?> getWorkerByToken (@RequestHeader(value = "Authorization") String token){
        String token2 = token.replace(AppConstants.TOKEN_PREFIX,"");
        WorkerResponseDTO workerResponseDTO = workerService.getWorkerByToken(token2);
        return  new ResponseEntity<>(workerResponseDTO,HttpStatus.OK);
    }
}
