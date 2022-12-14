package com.visachecker.server.applications.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ApplicationServiceTest {
    @InjectMocks
    private ApplicationService applicationService;
    @Mock
    private ApplicationRepository applicationDynamoDbRepository;

    @BeforeEach
    void setMockOutput() {
        when(applicationDynamoDbRepository.getAll()).thenReturn(List.of(
                new Application("41212", "XX", ApplicationCode.DP, "2022"),
                new Application("12425", "XX", ApplicationCode.VP, "2021")

        ));
    }

    public ApplicationServiceTest() {
        applicationService = new ApplicationService();
    }

    @Test
    public void SaveGeneratesId() {
        var id = applicationService.createApplication(new Application("41212", "XX", ApplicationCode.DP, "2022"));
        assertNotNull(id);
    }
}
