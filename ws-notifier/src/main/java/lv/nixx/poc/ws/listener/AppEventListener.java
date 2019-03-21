package lv.nixx.poc.ws.listener;

import lv.nixx.poc.ws.AppEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.stereotype.Service;

@Service
public class AppEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(AppEventListener.class);

    @EventListener
    public void onApplicationEvent(BrokerAvailabilityEvent event) {
        boolean brokerAvailable = event.isBrokerAvailable();
        LOG.info("BrokerAvailabilityEvent, isBrokerAvailable [{}]", brokerAvailable);
    }

}