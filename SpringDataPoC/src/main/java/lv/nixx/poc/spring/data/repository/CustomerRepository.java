package lv.nixx.poc.spring.data.repository;

import java.util.List;

import lv.nixx.poc.spring.data.domain.Customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	public List<Customer> findByLastName(String lastName);
	
//	@Query("SELECT e FROM Customer e left outer join CustomerExtension c on e.id=c.customer_id")
//	public List<Customer> findAllCustomers();
}
