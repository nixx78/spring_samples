package lv.nixx.poc.spring.inheritance;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppWithInheritanceTest {

    @Test
    void injectionAndInitTest() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppWithInheritance.class);
        ConcreteServiceAlpha alpha = ctx.getBean(ConcreteServiceAlpha.class);

        assertAll(
                () -> assertEquals("alpha.value :processed: alpha.value:external", alpha.process("alpha.value")),
                () -> assertEquals("Init: ConcreteServiceAlpha.param", alpha.getValueFromInit())
        );

        ConcreteServiceBeta beta = ctx.getBean(ConcreteServiceBeta.class);

        assertAll(
                () -> assertEquals("beta.value :processed: beta.value:external", beta.process("beta.value")),
                () -> assertEquals("Init: ConcreteServiceBeta.param", beta.getValueFromInit())
        );
    }
}
