package com.visachecker.server.status.infrastructure.http;

import com.visachecker.server.status.domain.Decision;
import com.visachecker.server.status.domain.Status;
import com.visachecker.server.status.domain.exceptions.ApplicationNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StatusResponseParserTests {
    private final String timeDocumentName = "time.html";
    private final String approvedDocumentName = "approved.html";
    private final String declinedDocumentName = "declined.html";
    private final String inProgressDocumentName = "inProgress.html";
    private final String notFoundDocumentName = "notFound.html";
    private final String errorDocumentName = "error.html";

    @Test
    public void responseWithApprovedIsParsed() throws URISyntaxException, IOException, ApplicationNotFoundException {
        Document document = getDocument(approvedDocumentName);
        var parser = new StatusResponseParser(document);
        Status status = parser.getStatus();
        assertEquals(Decision.APPROVED, status.decision());
    }

    @Test
    public void responseWithDeclinedIsParsed() throws URISyntaxException, IOException, ApplicationNotFoundException {
        Document document = getDocument(declinedDocumentName);
        var parser = new StatusResponseParser(document);
        Status status = parser.getStatus();
        assertEquals(Decision.DECLINED, status.decision());
    }

    @Test
    public void responseWithInProgressIsParsed() throws URISyntaxException, IOException, ApplicationNotFoundException {
        Document document = getDocument(inProgressDocumentName);
        var parser = new StatusResponseParser(document);
        Status status = parser.getStatus();
        assertEquals(Decision.IN_PROGRESS, status.decision());
    }

    @Test
    public void responseWithNotFoundThrowsNotFound() throws URISyntaxException, IOException {
        Document document = getDocument(notFoundDocumentName);
        var parser = new StatusResponseParser(document);
        assertThrows(ApplicationNotFoundException.class, parser::getStatus);
    }

    @Test
    public void responseWithTimeIsParsed() throws URISyntaxException, IOException {
        Document document = getDocument(timeDocumentName);
        var parser = new StatusResponseParser(document);
        var time = parser.getHoneyPotTime();
        assertEquals("1671567550|a605oWc2eKsG2ZIJrzHgzj7hbQcV2dzVcIZk5S96NRQ", time);
    }

    @Test
    public void responseWithErrorThrows() throws URISyntaxException, IOException {
        Document document = getDocument(errorDocumentName);
        var parser = new StatusResponseParser(document);
        assertThrows(IllegalStateException.class, parser::getStatus);
    }


    private Document getDocument(String fileName) throws URISyntaxException, IOException {
        var documentFile = new File(this.getClass().getResource(fileName).toURI());
        var document = Jsoup.parse(documentFile);
        return document;
    }
}
