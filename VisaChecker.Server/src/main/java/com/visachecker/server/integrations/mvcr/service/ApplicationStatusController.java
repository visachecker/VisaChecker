package com.visachecker.server.integrations.mvcr.service;

import com.visachecker.server.applications.domain.Application;
import com.visachecker.server.integrations.mvcr.domain.ApplicationStatusService;
import com.visachecker.server.integrations.mvcr.domain.Status;
import com.visachecker.server.integrations.mvcr.domain.exceptions.ApplicationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/applicationStatus")
public class ApplicationStatusController {

    @Autowired
    private ApplicationStatusService applicationStatusService;

    @PostMapping()
    public ResponseEntity<Object> getStatus(@RequestBody Application application) {
        try {
            return new ResponseEntity<>(applicationStatusService.getStatus(application), HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>("Server is still initializing", HttpStatus.SERVICE_UNAVAILABLE);
        } catch (ApplicationNotFoundException e) {
            return new ResponseEntity<>("Your application was not found", HttpStatus.NOT_FOUND);
        }
    }
}
