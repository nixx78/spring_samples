package lv.nixx.poc.spring.ioc;

import org.hamcrest.collection.ArrayMatching;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IoCTest {

    @Test
    void processTest() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Configuration.class);

        assertAll(
                () -> assertEquals(new Customer("Primary", "Primary"), ctx.getBean(Customer.class)),
                () -> assertEquals(new Customer("Jonh", "Smith"), ctx.getBean("john", Customer.class)),
                () -> assertEquals(new Customer("Merry", "Luiza"), ctx.getBean("merry", Customer.class)),
                () -> assertEquals("Hello Customer(name=Jonh, surname=Smith)!!!!", ctx.getBean("beanWithJohn", GenericHelloBean.class).sayHello()),
                () -> assertEquals("Hello Customer(name=Jonh, surname=Smith)!!!!", ctx.getBean(SayHelloToJohn.class).sayHello()),
                () -> assertEquals("Hello Customer(name=Primary, surname=Primary)!!!!", ctx.getBean(GenericHelloBean.class).sayHello()),
                () -> assertThat(ctx.getBeanNamesForType(Customer.class), ArrayMatching.arrayContainingInAnyOrder("john", "merry", "randomCustomer", "primary")),
                () ->  assertEquals("Hello Customer(name=Primary, surname=Primary)!!!!#Hello Customer(name=Jonh, surname=Smith)!!!!#Hello Customer(name=Merry, surname=Luiza)!!!!",
                        ctx.getBean(BeanWithInjectedBeans.class).getGreetings())
        );

        Customer c1 = ctx.getBean("randomCustomer", Customer.class);
        Customer c2 = ctx.getBean("randomCustomer", Customer.class);

        // Каждый раз получим новый экземпляр бина
        assertNotEquals(c1.hashCode(), c2.hashCode());
    }

}
