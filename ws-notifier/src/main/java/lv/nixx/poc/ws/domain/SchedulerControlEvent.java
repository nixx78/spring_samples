package lv.nixx.poc.ws.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class SchedulerControlEvent extends ApplicationEvent {

    private String action;

    public SchedulerControlEvent(Object source, String action) {
        super(source);
        this.action = action;
    }

}
