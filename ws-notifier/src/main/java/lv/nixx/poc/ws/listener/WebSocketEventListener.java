package lv.nixx.poc.ws.listener;

import java.util.Map;

import lv.nixx.poc.ws.AppEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
public class WebSocketEventListener {

	private static final Logger LOG = LoggerFactory.getLogger(WebSocketEventListener.class);

	private AppEventPublisher publisher;

	@Autowired
	public void setPublisher(AppEventPublisher publisher) {
		this.publisher = publisher;
	}

	@EventListener
	public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
		LOG.info("Subscribe");
		log(headerAccessor.toMap());

		publisher.startScheduler();
	}
	@EventListener
	public void onApplicationEvent(SessionUnsubscribeEvent sessionSubscribeEvent) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
		LOG.info("Unsubscribe");
		log(headerAccessor.toMap());

		publisher.stopScheduler();
	}

	private void log(Map<String, Object> map) {
		 map.forEach( (k, v) ->  LOG.info("\theader [{}] value [{}]", k, v) );
	}

}