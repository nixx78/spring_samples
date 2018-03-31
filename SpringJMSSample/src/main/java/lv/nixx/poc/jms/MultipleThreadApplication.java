
package lv.nixx.poc.jms;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

@SpringBootApplication
@EnableJms
public class MultipleThreadApplication {
	
	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		return factory;
	}

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(MultipleThreadApplication.class, args);

		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		ExecutorService pool = Executors.newFixedThreadPool(5);
		
		List<Future<String>> futures = Stream.iterate(1, i->i+1)
				.map(i->new Request(jmsTemplate, "Message: " + i))
				.limit(10)
				.map(pool::submit)
				.collect(Collectors.toList());
		
		for (Future<String> future : futures) {
			System.out.println("Synch response [" + future.get() + "]");
		}
		
		System.out.println("Existing...");
		pool.shutdown();
		context.close();
	}

	
	static class Request implements Callable<String> {
		
		private JmsTemplate jmsTemplate;
		private String message;

		Request(JmsTemplate jmsTemplate, String message) {
			this.jmsTemplate = jmsTemplate;
			this.message = message;
		}

		@Override
		public String call() throws Exception {
			
			MessageCreator msgCreator = new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(message);
				}
			};

			Message sendAndReceive = jmsTemplate.sendAndReceive("synch.queue.request.jmstemplate", msgCreator);
			return ((TextMessage)sendAndReceive).getText();
		}
		
	}

	
	


}
