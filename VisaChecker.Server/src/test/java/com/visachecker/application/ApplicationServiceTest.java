package com.visachecker.application;

import com.visachecker.application.applications.domain.Application;
import com.visachecker.application.applications.domain.ApplicationRepository;
import com.visachecker.application.applications.domain.ApplicationService;
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
                new Application("test-number"),
                new Application("test-number-2")

        ));
    }

    public ApplicationServiceTest() {
        applicationService = new ApplicationService();
    }

    @Test
    public void SaveGeneratesId() {
        var id = applicationService.createApplication(new Application("test"));
        assertNotNull(id);
    }
}
