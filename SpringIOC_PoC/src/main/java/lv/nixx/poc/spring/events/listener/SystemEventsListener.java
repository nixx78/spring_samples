package lv.nixx.poc.spring.events.listener;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SystemEventsListener {

    @EventListener
    public void contextStarter(ContextStartedEvent event) {
        System.out.println("ContextStartedEvent fired: " + event);
    }
    @EventListener
    public void contextStopped(ContextStoppedEvent event) {
        System.out.println("ContextStoppedEvent fired: " + event);
    }

}
