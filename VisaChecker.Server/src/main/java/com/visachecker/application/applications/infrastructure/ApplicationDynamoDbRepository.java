package com.visachecker.application.applications.infrastructure;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.visachecker.application.applications.domain.Application;
import com.visachecker.application.applications.domain.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationDynamoDbRepository implements ApplicationRepository {
    private final DynamoDBMapper mapper;

    @Autowired
    public ApplicationDynamoDbRepository(DynamoDBMapper mapper) {

        this.mapper = mapper;
    }

    @Override
    public Iterable<Application> getAll() {
        return mapper.scan(Application.class, new DynamoDBScanExpression());
    }

    @Override
    public void save(Application application) {
        mapper.save(application);
    }
}
