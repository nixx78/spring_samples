package lv.nixx.poc.spring.data.repository;

import java.util.List;

import lv.nixx.poc.spring.data.domain.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	public List<Customer> findByLastName(String lastName);
}
