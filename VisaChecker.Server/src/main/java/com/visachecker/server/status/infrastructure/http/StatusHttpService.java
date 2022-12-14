package com.visachecker.server.status.infrastructure.http;

import com.visachecker.server.applications.domain.Application;
import com.visachecker.server.status.domain.StatusService;
import com.visachecker.server.status.domain.Status;
import com.visachecker.server.status.domain.exceptions.ApplicationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusHttpService implements StatusService {
    @Autowired
    private StatusHttpClient client;
    @Autowired
    private TimeService timeService;

    @Override
    public Status getStatus(Application application) throws ApplicationNotFoundException {
        return client.getStatus(timeService.getTime(), application);
    }
}
