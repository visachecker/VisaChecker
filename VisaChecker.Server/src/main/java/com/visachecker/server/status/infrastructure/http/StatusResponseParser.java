package com.visachecker.server.status.infrastructure.http;

import com.visachecker.server.status.domain.Decision;
import com.visachecker.server.status.domain.Status;
import com.visachecker.server.status.domain.exceptions.ApplicationNotFoundException;
import org.jsoup.nodes.Document;


public class StatusResponseParser {
    private final Document document;

    public StatusResponseParser(Document document) {
        this.document = document;
    }

    public String getHoneyPotTime() {
        var timeElement = document.getElementsByAttributeValue("name", "honeypot_time").first();
        if (timeElement == null) {
            throw new IllegalArgumentException(); //TODO: proper exception
        }
        return timeElement.val();
    }

    public Status getStatus() throws ApplicationNotFoundException {
        var element = document.selectXpath("//section[@class='col-sm-12'][1]/div[1]/ul/li[1]/p/span/strong").first();
        if (element != null) {
            var state = element.childNode(0).attributes().get("#text");
            return new Status(switch (state) {
                case "Vyřízeno – POVOLENO" -> Decision.APPROVED;
                case "Vyřízeno – NEPOVOLENO" -> Decision.DECLINED;
                case "Zpracovává se" -> Decision.IN_PROGRESS;
                case "Nenalezeno" -> throw new ApplicationNotFoundException();
                default -> throw new IllegalArgumentException();
            });
        }
        throw new IllegalArgumentException(); //TODO: proper
    }
}
