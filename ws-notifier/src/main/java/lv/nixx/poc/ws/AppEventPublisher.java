package lv.nixx.poc.ws;

import lv.nixx.poc.ws.domain.SchedulerControlEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AppEventPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(AppEventPublisher.class);

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void startScheduler() {
        LOG.info("SchedulerControlEvent:start published");
        applicationEventPublisher.publishEvent(new SchedulerControlEvent(this, "start"));
    }

    public void stopScheduler() {
        LOG.info("SchedulerControlEvent:stop published");
        applicationEventPublisher.publishEvent(new SchedulerControlEvent(this, "stop"));
    }

}
