package com.visachecker.server.integrations.mvcr.infrastructure.http;

import com.visachecker.server.applications.domain.Application;
import com.visachecker.server.integrations.mvcr.domain.ApplicationStatusService;
import com.visachecker.server.integrations.mvcr.domain.Status;
import com.visachecker.server.integrations.mvcr.domain.exceptions.ApplicationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationStatusHttpService implements ApplicationStatusService {
    @Autowired
    private MvcrApplicationStatusHttpClient client;
    @Autowired
    private TimeService timeService;

    @Override
    public Status getStatus(Application application) throws ApplicationNotFoundException {
        return client.getStatus(timeService.getTime(), application);
    }
}
