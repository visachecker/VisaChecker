package com.visachecker.server.status.infrastructure.http;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class TimeService {
    private final StatusHttpClient client;
    private String time;
    private final Timer timer = new Timer();
    private final int refreshTimeMs = 60 * 60 * 1000;

    @Autowired
    public TimeService(@Autowired StatusHttpClient client) {
        this.client = client;
    }

    @PostConstruct
    private void setNewTime() {
        time = client.getHoneypotTime();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setNewTime();
            }
        }, refreshTimeMs);
    }

    public String getTime() {
        return time;
    }
}
