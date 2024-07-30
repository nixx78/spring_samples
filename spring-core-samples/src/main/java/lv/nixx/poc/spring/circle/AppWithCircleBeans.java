package lv.nixx.poc.spring.circle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Пример показывает как можно создавать бины которые зависят друг от друга: ServiceA -> ServiceB -> ServiceA
@Configuration
@ComponentScan(basePackageClasses = AppWithCircleBeans.class)
public class AppWithCircleBeans {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppWithCircleBeans.class);

        System.out.println(ctx.getBean(ServiceA.class).process());
        System.out.println(ctx.getBean(ServiceB.class).process());
    }

}
