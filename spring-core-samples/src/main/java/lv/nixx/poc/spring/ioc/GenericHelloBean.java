package lv.nixx.poc.spring.ioc;

abstract class GenericHelloBean {

    private final Customer customer;

    public GenericHelloBean(Customer customer) {
        this.customer = customer;
    }

    public String sayHello() {
        return "Hello " + customer + "!!!!";
    }

}
