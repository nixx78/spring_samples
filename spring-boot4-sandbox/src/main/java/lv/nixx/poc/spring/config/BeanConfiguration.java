package lv.nixx.poc.spring.config;

import lv.nixx.poc.spring.MyProcessorBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    MyProcessorBean createMyBean() {
        return new MyProcessorBean("Id:" + System.currentTimeMillis());
    }

}

