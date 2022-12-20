package com.visachecker.server.applications.service.models;

import com.visachecker.server.applications.domain.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {
    public ApplicationDTO toDto(Application application) {
        return new ApplicationDTO(
                application.getId(),
                application.getNumber(),
                application.getNumberFake(),
                application.getCode(),
                application.getYear()
        );
    }

    public Application toApplication(ApplicationCreationDTO applicationDTO) {
        return new Application(
                applicationDTO.number(),
                applicationDTO.numberFake(),
                applicationDTO.code(),
                applicationDTO.year()
        );
    }
}

