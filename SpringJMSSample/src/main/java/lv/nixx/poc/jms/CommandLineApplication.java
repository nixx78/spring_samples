package lv.nixx.poc.jms;

import java.util.Scanner;

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
public class CommandLineApplication {

	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		return factory;
	}

	public static void main(String[] args) throws JMSException {
		// Launch the application
		ConfigurableApplicationContext context = SpringApplication.run(CommandLineApplication.class, args);

		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		try (Scanner keyboard = new Scanner(System.in)) {
			String message = "";
			do {
				System.out.println("Enter an message text, or 'exit' for cancel");
				message = keyboard.nextLine();
				sendMessageAndLogResponse(jmsTemplate, message);
			} while (!message.equalsIgnoreCase("exit"));
		}
		
		System.out.println("Exiting...");
		context.close();
	}

	private static void sendMessageAndLogResponse(JmsTemplate jmsTemplate, String message) throws JMSException {
		MessageCreator msgCreator = new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		};

		Message sendAndReceive = jmsTemplate.sendAndReceive("synch.queue.request.jmstemplate", msgCreator);
		System.out.println("Synch response to request [" +  ((TextMessage)sendAndReceive).getText() + "]");
	}


}
