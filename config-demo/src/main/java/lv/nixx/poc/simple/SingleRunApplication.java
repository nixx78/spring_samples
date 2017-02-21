package lv.nixx.poc.simple;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SingleRunApplication.class)
public class SingleRunApplication {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(SingleRunApplication.class)) {
			
			Engine e = ctx.getBean(Engine.class);
			e.process();
		}

	}

}
