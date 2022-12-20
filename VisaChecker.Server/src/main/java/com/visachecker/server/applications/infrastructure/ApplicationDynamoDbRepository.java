package com.visachecker.server.applications.infrastructure;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.visachecker.server.applications.domain.Application;
import com.visachecker.server.applications.domain.ApplicationRepository;
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
    public Application getApplicationById(String id) {
        return mapper.load(Application.class, id, new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT));
    }

    @Override
    public void save(Application application) {
        mapper.save(application);
    }
}
