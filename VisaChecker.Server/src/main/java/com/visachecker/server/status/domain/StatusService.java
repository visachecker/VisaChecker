package com.visachecker.server.status.domain;

import com.visachecker.server.applications.domain.Application;
import com.visachecker.server.status.domain.exceptions.ApplicationNotFoundException;

public interface StatusService {
    Status getStatus(Application application) throws ApplicationNotFoundException;
}
