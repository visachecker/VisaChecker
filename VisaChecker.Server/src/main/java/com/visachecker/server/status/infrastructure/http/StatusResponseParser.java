package com.visachecker.server.status.infrastructure.http;

import com.visachecker.server.status.domain.Decision;
import com.visachecker.server.status.domain.Status;
import com.visachecker.server.status.domain.exceptions.ApplicationNotFoundException;
import org.jsoup.nodes.Document;

import java.util.Map;


public class StatusResponseParser {
    private final Document document;
    private final String containingElementTag = "strong";
    private final String containingElementValueNotFoundText = "Nenalezeno";

    private final Map<Decision, String> decisionToElementTextMap = Map.of(
            Decision.APPROVED, "Vyřízeno – POVOLENO",
            Decision.DECLINED, "Vyřízeno – NEPOVOLENO",
            Decision.IN_PROGRESS, "Zpracovává se"
    );

    public StatusResponseParser(Document document) {
        this.document = document;
    }

    public String getHoneyPotTime() {
        var timeElement = document.getElementsByAttributeValue("name", "honeypot_time").first();
        if (timeElement == null) {
            throw new IllegalStateException("No time element in status response");
        }
        return timeElement.val();
    }

    public Status getStatus() throws ApplicationNotFoundException {
        for (var decision : decisionToElementTextMap.keySet()) {
            if (statusElementContainsText(decisionToElementTextMap.get(decision))) {
                return new Status(decision);
            }
        }

        if (statusElementContainsText(containingElementValueNotFoundText)) throw new ApplicationNotFoundException();

        throw new IllegalStateException("No status element in status response");
    }

    private boolean statusElementContainsText(String text) {
        return document.select("%s:contains(%s)".formatted(containingElementTag, text)).size() == 1;
    }
}
