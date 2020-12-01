package lv.nixx.poc.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    @Cacheable("customers")
    public Customer getById(Long id) {

        return new Customer()
                .setId(id)
                .setName("Name" + id);
    }



}
