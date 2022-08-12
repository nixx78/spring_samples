package lv.nixx.poc.spring.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BeanWithInjectedBeans {

    private GenericHelloBean primaryBean;
    private SayHelloToJohn sayHelloToJohn;
    private GenericHelloBean sayHelloToMarry;

    @Autowired
    public void setPrimaryBean(GenericHelloBean primaryBean) {
        this.primaryBean = primaryBean;
    }

    @Autowired
    @Qualifier("beanWithJohn")
    public void setSayHelloToJohn(SayHelloToJohn sayHelloToJohn) {
        this.sayHelloToJohn = sayHelloToJohn;
    }

    @Autowired
    public void setSayHelloToMarry(SayHelloToMarry sayHelloToMarry) {
        this.sayHelloToMarry = sayHelloToMarry;
    }

    public String getGreetings() {
        return primaryBean.sayHello() + "#" + sayHelloToJohn.sayHello() + "#" + sayHelloToMarry.sayHello();
    }

}
