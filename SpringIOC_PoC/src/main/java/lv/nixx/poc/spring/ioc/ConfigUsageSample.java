package lv.nixx.poc.spring.ioc;

import org.springframework.context.annotation.*;

@Configuration("appConfig")
@ComponentScan(basePackages="lv.nixx.spring")
public class ConfigUsageSample {
	
	@Bean
	public Customer john(){
		return new Customer("Jonh","Smith");
	}

	@Bean
	public Customer mary(){
		return new Customer("Mary","Luiza");
	}
	
	@Bean
	@Scope("prototype")
	public Customer randomCustomer(){
		return new Customer("n_" + System.currentTimeMillis(), "s_" + System.currentTimeMillis() );
	}
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigUsageSample.class);
		
		System.out.println(ctx.getBean("john"));
		System.out.println(ctx.getBean("mary"));
		
		ConfigUsageSample bean = ctx.getBean("appConfig", ConfigUsageSample.class);
		System.out.println(bean.john() + "!!!!!");
		
		
		ComplexBean complexBean = (ComplexBean) ctx.getBean("beanWithJohn");
		complexBean.sayHello();
		
		String[] names = ctx.getBeanNamesForType(Customer.class);
		for (String n : names) {
			System.out.println(n);
		}
		
		Customer c1 = ctx.getBean("randomCustomer", Customer.class);
		System.out.println(c1.hashCode() + ":" + c1);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Customer c2 = ctx.getBean("randomCustomer", Customer.class);
		System.out.println(c2.hashCode() + ":" + c2);
	}
	
}
