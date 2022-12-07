package com.visachecker.application.applications.infrastructure;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.visachecker.application.applications.domain.Application;
import com.visachecker.application.applications.domain.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationDynamoDbRepository implements ApplicationRepository {
    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public ApplicationDynamoDbRepository(DynamoDBMapper dynamoDBMapper) {

        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public Iterable<Application> getAll() {
        return dynamoDBMapper.query(Application.class, new DynamoDBQueryExpression<>());
    }

    @Override
    public void save(Application application) {
        dynamoDBMapper.save(application);
    }
}
