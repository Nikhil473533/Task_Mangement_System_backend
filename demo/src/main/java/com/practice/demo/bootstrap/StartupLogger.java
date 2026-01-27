package com.practice.demo.bootstrap;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupLogger implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger log =
            LoggerFactory.getLogger(StartupLogger.class);

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("============================================================");
        log.info("=== APPLICATION STARTING | PID={} | TIME={} ===",
                ProcessHandle.current().pid(),
                LocalDateTime.now());
        log.info("============================================================");
    }

}
