package lv.nixx.poc.spring.inheritance;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
abstract class GenericService {

    private String valueFromInit;

    private ExternalService externalService;
    private final String paramFromChild;

    @Autowired
    public void setExternalService(ExternalService externalService) {
        this.externalService = externalService;
    }

    public GenericService(String paramFromChild) {
        this.paramFromChild = paramFromChild;
    }

    @PostConstruct
    public void init() {
        this.valueFromInit = "Init: " + paramFromChild;
    }

    public String process(String p) {
        return p + " :processed: " + externalService.externalCall(p);
    }


}
