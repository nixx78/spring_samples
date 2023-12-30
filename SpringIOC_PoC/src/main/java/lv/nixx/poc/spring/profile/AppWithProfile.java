package lv.nixx.poc.spring.profile;

import lv.nixx.poc.spring.complex.service.IService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppWithProfile {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigWithMultipleProfiles.class);

        IService serviceForProfile = ctx.getBean("serviceForProfile", IService.class);
        System.out.println(serviceForProfile.process("123456"));

        ServiceForDevProfile serviceForDevProfile = ctx.getBean("serviceForProfile", ServiceForDevProfile.class);
        System.out.println(serviceForDevProfile.process("reg"));
    }

}
