package lv.nixx.poc.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    private static final Logger LOG = LoggerFactory.getLogger("CustomerRepository");

    @Override
    @Cacheable(value = "customers", sync = true, key = "#id")
    public Customer getById(Long id) {

        LOG.info("Get by ID: real call for id [{}]", id);

        return new Customer()
                .setId(id)
                .setName("Name" + id);
    }

    @Override
    @CacheEvict(cacheNames = "customers", allEntries = true)
    public void clearCache() {}


}
