package lv.nixx.poc.simple;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingleRunApplication {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(SimpleConfig.class)) {
			
			Engine e = ctx.getBean(Engine.class);
			e.process();
		}

	}

}
