package lv.nixx.poc.spring.complex;

import lv.nixx.poc.spring.complex.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.toMap;

@Service
public class OrchestrationAnotherAutowire {

    private final Collection<IService> services;

    @Autowired
    public OrchestrationAnotherAutowire(Collection<IService> services) {
        this.services = services;
    }

    public Map<Source, Dto> process(String request) {
        System.out.println("Cal service with injected services:");
        return services.stream()
                .collect(toMap(IService::getSource, t -> t.process(request), (t1, t2) -> t2, TreeMap::new));
    }


}
