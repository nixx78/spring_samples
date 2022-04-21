package lv.nixx.poc.spring.complex;

import lv.nixx.poc.spring.ioc.ComplexBean;
import lv.nixx.poc.spring.ioc.ConfigUsageSample;
import lv.nixx.poc.spring.ioc.Customer;
import org.hamcrest.collection.ArrayMatching;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {

    @Test
    void processTest() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigUsageSample.class);

        assertAll(
                () -> assertEquals(new Customer("Jonh", "Smith"), ctx.getBean("john", Customer.class)),
                () -> assertEquals(new Customer("Mary", "Luiza"), ctx.getBean("mary", Customer.class)),
                () -> assertNotNull(ctx.getBean("beanWithJohn", ComplexBean.class))
        );

        assertThat(ctx.getBeanNamesForType(Customer.class), ArrayMatching.arrayContainingInAnyOrder("john", "mary", "randomCustomer"));

        Customer c1 = ctx.getBean("randomCustomer", Customer.class);
        Customer c2 = ctx.getBean("randomCustomer", Customer.class);

        assertNotEquals(c1.hashCode(), c2.hashCode());
    }

}
