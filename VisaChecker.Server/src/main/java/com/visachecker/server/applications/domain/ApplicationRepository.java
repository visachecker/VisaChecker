package com.visachecker.server.applications.domain;

public interface ApplicationRepository {
    Iterable<Application> getAll();

    void save(Application application);
}
