package com.visachecker.application.applications.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public Iterable<Application> getApplications() {
        return applicationRepository.getAll();
    }

    public String createApplication(Application application) {
        String id = UUID.randomUUID().toString();
        application.setId(id);
        applicationRepository.save(application);
        return id;
    }
}
