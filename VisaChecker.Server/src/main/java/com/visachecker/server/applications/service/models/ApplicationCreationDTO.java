package com.visachecker.server.applications.service.models;

import com.visachecker.server.applications.domain.ApplicationCode;

public record ApplicationCreationDTO(
        String number,
        String numberFake,
        ApplicationCode code,
        String year
) {
}