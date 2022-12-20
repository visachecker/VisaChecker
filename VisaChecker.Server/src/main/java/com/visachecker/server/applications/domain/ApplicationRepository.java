package com.visachecker.server.applications.domain;

public interface ApplicationRepository {
    Iterable<Application> getAll();

    Application getApplicationById(String id);

    void save(Application application);
}
