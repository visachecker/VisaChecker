package com.visachecker.server.status.infrastructure.http;

import com.visachecker.server.applications.domain.Application;
import com.visachecker.server.status.domain.Status;
import com.visachecker.server.status.domain.exceptions.ApplicationNotFoundException;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StatusHttpClient {
    private final String path = "https://frs.gov.cz/cs/ioff/application-status";

    public String getHoneypotTime() {
        try {
            var document = Jsoup.connect(path).get();
            return new StatusResponseParser(document).getHoneyPotTime();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Status getStatus(String honeyPotTime, Application application) throws ApplicationNotFoundException {
        try {
            var document = Jsoup.connect(path)
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8").
                    data("ioff_application_number", application.getNumber()).
                    data("ioff_application_number_fake", application.getNumberFake()).
                    data("ioff_application_code", application.getCode().name()).
                    data("ioff_application_year", application.getYear()).
                    data("op", "Ověřit").
                    data("form_id", "ioff_application_status_form").
                    data("honeypot_time", honeyPotTime)
                    .post();

            return new StatusResponseParser(document).getStatus();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
