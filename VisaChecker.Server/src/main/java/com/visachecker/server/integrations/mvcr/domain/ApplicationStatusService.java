package com.visachecker.server.integrations.mvcr.domain;

import com.visachecker.server.applications.domain.Application;
import com.visachecker.server.integrations.mvcr.domain.exceptions.ApplicationNotFoundException;

public interface ApplicationStatusService {
    Status getStatus(Application application) throws ApplicationNotFoundException;
}
