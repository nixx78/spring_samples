package lv.nixx.poc.ps.controller;

import lv.nixx.poc.ps.service.ApplicationRestartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ApplicationLifecycleController {

    private final ApplicationEventPublisher eventPublisher;
    private final ApplicationRestartService restartService;

    @Autowired
    public ApplicationLifecycleController(ApplicationEventPublisher eventPublisher, ApplicationRestartService restartService) {
        this.eventPublisher = eventPublisher;
        this.restartService = restartService;
    }

    @GetMapping("/refresh")
    public void refreshApplication() {
        eventPublisher.publishEvent(new RefreshEvent(this, "Refresh environment", "Refresh environment: " + new Date()));
    }

    @GetMapping("/restart")
    public void restartApplication() {
        restartService.restart();
    }



}
