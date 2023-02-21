package lv.nixx.poc.spring;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XMLContextTest {

    @Test
    void xmlContextTest() {

        ApplicationContext context = new ClassPathXmlApplicationContext("app.xml");
        HelloBean bean = context.getBean(HelloBean.class);

        assertEquals("HelloBean says hello to:Name.Value,Surname.Value", bean.getHello());
    }

}
