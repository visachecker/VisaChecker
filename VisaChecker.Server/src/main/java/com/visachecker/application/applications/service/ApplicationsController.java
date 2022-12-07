package com.visachecker.application.applications.service;

import com.visachecker.application.applications.domain.Application;
import com.visachecker.application.applications.domain.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applications")
public class ApplicationsController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public Iterable<Application> getAllApplications() {
        return applicationService.getApplications();
    }

    @PostMapping
    public String createApplication(@RequestBody Application application) {
        return applicationService.createApplication(application);
    }
}
