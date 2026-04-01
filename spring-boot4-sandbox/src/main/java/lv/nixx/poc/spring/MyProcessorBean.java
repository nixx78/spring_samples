package lv.nixx.poc.spring;

public class MyProcessorBean {

    private final String id;

    public MyProcessorBean(String id) {
        this.id = id;
    }

    public String process(String request) {
        return "Request: [%s] processed by: %s%n".formatted(request, id);
    }


}
