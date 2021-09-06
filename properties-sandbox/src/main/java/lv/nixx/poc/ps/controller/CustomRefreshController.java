package lv.nixx.poc.ps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CustomRefreshController {

    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public CustomRefreshController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/reloadProperties")
    public void reloadProperties() {
        eventPublisher.publishEvent(new RefreshEvent(this, "Refresh environment", "Refresh environment: " + new Date()));
    }

}
