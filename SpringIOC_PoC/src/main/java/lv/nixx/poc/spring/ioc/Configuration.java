package lv.nixx.poc.spring.ioc;

import org.springframework.context.annotation.*;

@org.springframework.context.annotation.Configuration("appConfig")
@ComponentScan(basePackages = "lv.nixx.poc.spring.ioc")
public class Configuration {

    @Bean
    public Customer john() {
        return new Customer("Jonh", "Smith");
    }

    @Bean
    public Customer merry() {
        return new Customer("Merry", "Luiza");
    }

    @Bean
    @Primary
    public Customer primary() {
        return new Customer("Primary", "Primary");
    }

    @Bean
    @Scope("prototype")
    public Customer randomCustomer() {
        return new Customer("n_" + System.nanoTime(), "s_" + System.nanoTime());
    }
}
