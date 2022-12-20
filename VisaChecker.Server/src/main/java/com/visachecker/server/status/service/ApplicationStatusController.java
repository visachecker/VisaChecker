package com.visachecker.server.status.service;

import com.visachecker.server.applications.domain.Application;
import com.visachecker.server.status.domain.StatusService;
import com.visachecker.server.status.domain.exceptions.ApplicationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applicationStatus")
public class ApplicationStatusController {

    @Autowired
    private StatusService statusService;

    @PostMapping()
    public ResponseEntity<Object> getStatus(@RequestBody Application application) {
        try {
            return new ResponseEntity<>(statusService.getStatus(application), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>("Server is still initializing: %s".formatted(e.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (ApplicationNotFoundException e) {
            return new ResponseEntity<>("Your application was not found", HttpStatus.NOT_FOUND);
        }
    }
}
