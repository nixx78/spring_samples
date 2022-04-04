package lv.nixx.poc.spring.complex;

import lv.nixx.poc.spring.complex.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toMap;

@Service
public class OrchestrationService {

    private final Set<IService> services;

    @Autowired
    public OrchestrationService(ApplicationContext appContext) {
        Map<String, IService> beansOfType = appContext.getBeansOfType(IService.class);
        this.services = new HashSet<>(beansOfType.values());
    }

    public Map<Source, Dto> process(String request) {
        return services.stream()
                .collect(toMap(IService::getSource, t -> t.process(request), (t1, t2) -> t2, TreeMap::new));
    }


}
