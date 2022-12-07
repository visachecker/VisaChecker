package com.visachecker.application.applications.domain;

public interface ApplicationRepository {
    Iterable<Application> getAll();

    void save(Application application);
}
