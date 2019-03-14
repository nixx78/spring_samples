package lv.nixx.poc.ws;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
public class AppController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AppController.class);
	
    private SimpMessagingTemplate template;
    
    @Autowired   
    public void setTemplate(SimpMessagingTemplate template) {
		this.template = template;
	}
    
    @SubscribeMapping("/topic/greetings")
    @ResponseBody
    public Time subscribe() {
    	LOG.info("Subscription to topic '/topic/greetings'");
    	return new Time("Subscribe is " + new Date());
    }

    @MessageMapping("message")
    @ResponseBody
//    @SendTo("/topic/greetings")
    public Time greeting(Message message) throws Exception {
    	LOG.info("Message received: {}", message);
        return new Time("Hello, " + HtmlUtils.htmlEscape(message.getText()) + "!");
    }
   
    @Scheduled(fixedDelay=30_000)
    public void sentTime() {
    	final String payload = "Now is " + new Date();
    	LOG.info("Payload: {}", payload );
		template.convertAndSend("/app/topic/greetings", new Time(payload));
    }
    

}