package lv.nixx.poc.ws;

import lv.nixx.poc.ws.domain.Action;
import lv.nixx.poc.ws.domain.SchedulerControlEvent;
import lv.nixx.poc.ws.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@Component
public class SchedulerService {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerService.class);

    private SimpMessagingTemplate template;
    private volatile ScheduledFuture<?> scheduledFuture;
    private ThreadPoolTaskScheduler poolTaskScheduler;

    @Autowired
    public void setTemplate(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Autowired
    public void setPoolTaskScheduler(ThreadPoolTaskScheduler poolTaskScheduler) {
        this.poolTaskScheduler = poolTaskScheduler;
    }

    @EventListener
    public void onSchedulerEvent(SchedulerControlEvent event) {
        LOG.info("Event fired [{}]", event);

        if (event.getAction().equalsIgnoreCase("start")) {

            if (scheduledFuture!=null) {
                LOG.info("Scheduler already started");
                return;
            }

            Runnable t = () -> {
                final String payload = "Now is " + new Date();
                LOG.info("Payload: {}", payload );
                template.convertAndSend("/topic/message", new Message(Action.MESSAGE, HtmlUtils.htmlEscape(payload)));
            };
            scheduledFuture = poolTaskScheduler.scheduleWithFixedDelay(t, 5_000);
            LOG.info("Scheduler started");
        } else {
            if (scheduledFuture!=null) {
                scheduledFuture.cancel(true);
                LOG.info("Scheduler stopped");
                scheduledFuture = null;
            }
        }

    }



}
