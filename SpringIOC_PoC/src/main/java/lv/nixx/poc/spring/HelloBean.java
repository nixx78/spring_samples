package lv.nixx.poc.spring;

import lombok.Setter;

@Setter
public class HelloBean {

    private String person;

    public String getHello() {
        return "HelloBean says hello to:" + person;
    }

}
