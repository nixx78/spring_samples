package lv.nixx.poc.spring.inheritance;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "lv.nixx.poc.spring.inheritance")
public class AppWithInheritance {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppWithInheritance.class);

        ConcreteServiceAlpha alpha = ctx.getBean(ConcreteServiceAlpha.class);
        System.out.println(alpha.process("alpha.value"));
        System.out.println(alpha.getValueFromInit());

        System.out.println("----------------------------");

        ConcreteServiceBeta beta = ctx.getBean(ConcreteServiceBeta.class);
        System.out.println(beta.process("beta.value"));
        System.out.println(beta.getValueFromInit());
    }

}
