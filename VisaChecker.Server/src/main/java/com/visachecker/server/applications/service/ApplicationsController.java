package com.visachecker.server.applications.service;

import com.visachecker.server.applications.domain.ApplicationService;
import com.visachecker.server.applications.service.models.ApplicationCreationDTO;
import com.visachecker.server.applications.service.models.ApplicationDTO;
import com.visachecker.server.applications.service.models.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/applications")
public class ApplicationsController {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ApplicationMapper mapper;

    @GetMapping
    public Iterable<ApplicationDTO> getAllApplications() {
        return StreamSupport
                .stream(applicationService.getApplications().spliterator(), false)
                .map(mapper::toDto)
                .toList();
    }

    @PostMapping
    public String createApplication(@RequestBody ApplicationCreationDTO application) {
        return applicationService.createApplication(mapper.toApplication(application));
    }
}
