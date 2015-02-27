package lv.nixx.poc.spring.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("beanWithJohn")
// Данный бин будет создан с именем - "beanWithJohn" 
public class ComplexBean {
	
	@Autowired
	@Qualifier("john")
	private Customer john; // Сюда будет подставлен из контекста бин с именем "john"
	
	public void sayHello(){
		System.out.println("Hello " + john + "!!!!");
	}

}
