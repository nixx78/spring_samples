package lv.nixx.poc.spring;

import lv.nixx.poc.spring.complex.service.IService;
import lv.nixx.poc.spring.profile.ConfigWithMultipleProfiles;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppWithProfile {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigWithMultipleProfiles.class);

        IService serviceForProfile = ctx.getBean("serviceForProfile", IService.class);
        System.out.println(serviceForProfile.process("123456"));
    }

}
