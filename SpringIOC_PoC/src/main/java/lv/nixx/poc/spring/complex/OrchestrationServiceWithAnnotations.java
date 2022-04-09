package lv.nixx.poc.spring.complex;

import lv.nixx.poc.spring.complex.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static java.util.stream.Collectors.toMap;

@Service
public class OrchestrationServiceWithAnnotations {

    private static final Logger LOG = LoggerFactory.getLogger(OrchestrationServiceWithAnnotations.class);

    private final Set<IService> services = new HashSet<>();

    @Autowired
    public OrchestrationServiceWithAnnotations(ApplicationContext appContext) {
        Map<String, Object> beansWithAnnotation = appContext.getBeansWithAnnotation(ServiceForProcessor.class);
        LOG.info("Found beans with annotation: {}", beansWithAnnotation);
        for (Object s : beansWithAnnotation.values()) {
            if (s instanceof IService) {
                services.add((IService) s);
            }
        }
    }

    public Map<Source, Dto> process(String request) {
        return services.stream()
                .collect(toMap(IService::getSource, t -> t.process(request), (t1, t2) -> t2, TreeMap::new));
    }


}
