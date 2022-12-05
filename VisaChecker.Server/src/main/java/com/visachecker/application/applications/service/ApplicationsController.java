package com.visachecker.application.applications.service;

import com.visachecker.application.applications.domain.Application;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationsController {

    @GetMapping
    public List<Application> GetAllApplications() {
        return List.of(new Application("sad", "dasa"));
    }
}
