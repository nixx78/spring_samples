package lv.nixx.poc.ws;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
public class WebSocketEventListener {

	private static final Logger LOG = LoggerFactory.getLogger(WebSocketEventListener.class);

	@EventListener
	public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
		LOG.info("Subscribe");
		log(headerAccessor.toMap());
	}
	@EventListener
	public void onApplicationEvent(SessionUnsubscribeEvent sessionSubscribeEvent) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
		LOG.info("Unsubscribe");
		log(headerAccessor.toMap());
	}

	private void log(Map<String, Object> map) {
		 map.entrySet().forEach( e -> {
			 LOG.info("\theader [{}] value [{}]", e.getKey(), e.getValue());
		 });
	}

}