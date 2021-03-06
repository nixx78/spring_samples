package lv.nixx.poc.spring;

import static org.junit.Assert.*;
import lv.nixx.poc.spring.config.AppConfigDev;
import lv.nixx.poc.spring.config.AppConfigTest;
import lv.nixx.poc.spring.config.MainAppConfig;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {
	
	@Test
	public void printBeans(){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.getEnvironment().setActiveProfiles("dev");
		ctx.register(MainAppConfig.class, AppConfigTest.class, AppConfigDev.class);
		ctx.refresh();
		
        String[] all = ctx.getBeanDefinitionNames();
        for (String b: all) {
			System.out.println(b);
		}
        
	}

	@Test
	public void registerConfigApproach() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.getEnvironment().setActiveProfiles("dev");
		ctx.register(MainAppConfig.class, AppConfigTest.class, AppConfigDev.class);
		ctx.refresh();
		
		assertEquals("profile [dev]", ctx.getBean(ProfiledBean.class).toString());
		assertEquals("name [default]",ctx.getBean("mainBean").toString());
	}

	@Test
	public void registerPackageScanApproach() {
		System.setProperty("spring.profiles.active", "test");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("lv.nixx.poc.spring.config");
		
		assertEquals("profile [test]", ctx.getBean(ProfiledBean.class).toString());
		assertEquals("name [namedBean]",ctx.getBean("namedBean").toString());
	}

}
