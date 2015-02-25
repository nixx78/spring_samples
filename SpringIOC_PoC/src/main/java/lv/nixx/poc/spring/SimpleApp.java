package lv.nixx.poc.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("app.xml");
		HelloBean bean = context.getBean(HelloBean.class);
		bean.sayHello();
		
		((ConfigurableApplicationContext)context).close();
	}

}
