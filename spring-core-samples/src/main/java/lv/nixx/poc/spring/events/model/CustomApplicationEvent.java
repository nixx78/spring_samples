package lv.nixx.poc.spring.events.model;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CustomApplicationEvent extends ApplicationEvent {

    private final String messageToSender;
    private final boolean success;

    public CustomApplicationEvent(Object source, String messageToSender, boolean success) {
        super(source);
        this.messageToSender = messageToSender;
        this.success = success;
    }
}
