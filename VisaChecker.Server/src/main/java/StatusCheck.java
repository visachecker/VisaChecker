package main.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

//Getting status of application by using Jsoup
public class StatusCheck {

    public static String check(String applicationNumImport, String xxFieldImport, String applicationTypeImport, String yearImport) {

        final String errorStatus = "Something went wrong";
        enum Status {
            APPROVED("Approved"),
            REJECTED("Rejected"),
            NOT_FOUND("Not found"),
            IN_PROCESS("In Process");
        
            private String value;
            public String getValue() {
                return value;
                }
            private Status(String value) {
                this.value = value;
            } 
        }

        Document document = null;
        try {
            document = Jsoup.connect("https://frs.gov.cz/en/ioff/application-status")
                    .data("ioff_application_number", applicationNumImport)
                    .data("ioff_application_number_fake", xxFieldImport)
                    .data("ioff_application_code", applicationTypeImport)
                    .data("ioff_application_year", yearImport)
                    .data("form_id", "ioff_application_status_form")
                    .data("honeypot_time", "1617192329|wrKL35XoiHwzqAlduShVGVGrXQjqg7-0i9sEfXbTyv8")
                    //.cookies(loginForm.cookies())
                    .post();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String approved = document.select("div:contains(APPROVED)").text();
        String rejected = document.select("div:contains(REJECTED)").text();
        String notFound = document.select("div:contains(Not found)").text();
        String inProcess = document.select("div:contains(In Process)").text();

        if(!approved.isBlank()){
            return Status.APPROVED.getValue();
        }
        if (!rejected.isBlank()) {
            return Status.REJECTED.getValue();
        }
        if (!notFound.isBlank()) {
            return Status.NOT_FOUND.getValue();
        }
        if (!inProcess.isBlank()) {
            return Status.IN_PROCESS.getValue();
        }        
        
        return errorStatus;
    }
}
