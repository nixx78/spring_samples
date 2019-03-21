package lv.nixx.poc.ws;

import java.util.Date;

import lv.nixx.poc.ws.domain.Action;
import lv.nixx.poc.ws.domain.ActionRequest;
import lv.nixx.poc.ws.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class AppController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AppController.class);
	
    private AppEventPublisher publisher;

	@Autowired
    public void setPublisher(AppEventPublisher publisher) {
        this.publisher = publisher;
    }

    @SubscribeMapping("/topic/message")
    public Message subscribe() {
    	return new Message(Action.MESSAGE,"Subscribe is " + new Date());
    }

    @MessageMapping("scheduler")
    @SendTo("/topic/message")
    public Message doAction(ActionRequest actionRequest) {
    	LOG.info("ActionRequest received: {}", actionRequest);

    	Action action;
    	if (actionRequest.getAction().equalsIgnoreCase("start")) {
            publisher.startScheduler();
            action = Action.START;
        } else {
            publisher.stopScheduler();
            action = Action.STOP;
        }

        return new Message(action,"ActionRequest, [" + HtmlUtils.htmlEscape(actionRequest.getAction()) + "] completed!");
    }

    @GetMapping("/scheduler/{action}")
    public String controlScheduler(@PathVariable String action) {
        if (action.equalsIgnoreCase("start")) {
            publisher.startScheduler();
        } else {
            publisher.stopScheduler();
        }
        return "ActionRequest: " + action + " completed";
    }


}