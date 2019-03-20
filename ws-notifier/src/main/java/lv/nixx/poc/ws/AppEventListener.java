package lv.nixx.poc.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@Service
public class AppEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(AppEventListener.class);

    private ThreadPoolTaskScheduler poolTaskScheduler;
    private SimpMessagingTemplate template;

    @Autowired
    public void setTemplate(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Autowired
    public void setPoolTaskScheduler(ThreadPoolTaskScheduler poolTaskScheduler) {
        this.poolTaskScheduler = poolTaskScheduler;
    }

    private ScheduledFuture<?> scheduledFuture;


    @EventListener
    public void onApplicationEvent(BrokerAvailabilityEvent event) {
        boolean brokerAvailable = event.isBrokerAvailable();
        LOG.info("BrokerAvailabilityEvent, isBrokerAvailable [{}]", brokerAvailable);


        if (brokerAvailable) {
            Runnable t = () -> {
                final String payload = "Now is " + new Date();
                LOG.info("Payload: {}", payload );
                template.convertAndSend("/topic/time", new Time(payload));
            };
            scheduledFuture = poolTaskScheduler.scheduleWithFixedDelay(t, 30_000);
            LOG.info("Scheduler started");
        } else {
            if (scheduledFuture!=null) {
                scheduledFuture.cancel(true);
                LOG.info("Scheduler stopped");
            }
        }

    }


}