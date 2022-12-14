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
    private boolean ready = false;
    private final Timer timer = new Timer();
    private final int maturityDelay = 30_000;

    @Autowired
    public TimeService(@Autowired StatusHttpClient client) {
        this.client = client;
    }

    @PostConstruct
    private void setNewTime() {
        //Make atomic, when more threads write
        time = client.getHoneypotTime();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ready = true;
            }
        }, maturityDelay);
    }

    public String getTime() {
        if (!ready) {
            throw new IllegalStateException("Can't access honeypot time before maturity");
        }
        return time;
    }
}
