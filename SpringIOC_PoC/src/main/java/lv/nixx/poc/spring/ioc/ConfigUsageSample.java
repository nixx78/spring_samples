package lv.nixx.poc.spring.ioc;

import org.springframework.context.annotation.*;

@Configuration("appConfig")
@ComponentScan(basePackages = "lv.nixx.poc.spring.ioc")
public class ConfigUsageSample {

    @Bean
    public Customer john() {
        return new Customer("Jonh", "Smith");
    }

    @Bean
    public Customer mary() {
        return new Customer("Mary", "Luiza");
    }

    @Bean
    @Scope("prototype")
    public Customer randomCustomer() {
        return new Customer("n_" + System.nanoTime(), "s_" + System.nanoTime());
    }
}
