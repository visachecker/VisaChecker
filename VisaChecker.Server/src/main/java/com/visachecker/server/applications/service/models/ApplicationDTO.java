package com.visachecker.server.applications.service.models;

import com.visachecker.server.applications.domain.ApplicationCode;

public record ApplicationDTO(
        String id,
        String number,
        String numberFake,
        ApplicationCode code,
        String year
) {
}