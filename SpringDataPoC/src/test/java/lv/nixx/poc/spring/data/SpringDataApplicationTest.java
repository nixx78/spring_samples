package lv.nixx.poc.spring.data;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import lv.nixx.poc.spring.data.domain.*;
import lv.nixx.poc.spring.data.repository.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfiguration.class)
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class SpringDataApplicationTest {
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TypeRepository typeRepository;
	@Autowired
	private CustomerExtensionRepository customerExtensionRepository;
	@Autowired
	private AdressRepository adressRepository;
	
	@Autowired
	private SimpleDAO simpleDao;
	
	@Autowired
	private EntityManager manager;
	
	@Test
	@Rollback(false)
	public void testDAO() {
		
		customerRepository.deleteAll();
		
		final CustomerType simpleCustomer = new CustomerType("simple","Simple Customer");
		final CustomerType vipCustomer = new CustomerType("vip","Simple Customer");

		// save a couple of customers
		Customer c1 = new Customer("Jack", "Bauer", simpleCustomer);
		c1.setExtension(new CustomerExtension("Jack's additional data"));

		Customer c2 = new Customer("Chloe", "O'Brian", vipCustomer);
		Customer c3 = new Customer("Nikolas", "Smith", simpleCustomer);
		c3.setExtension(new CustomerExtension("Nikolas's additional data"));
		
		c3.addAdress(new Adress("N1 line1", "N1 line2"));
		c3.addAdress(new Adress("N2 line1", "N2 line2"));

		simpleDao.save(c1);
		simpleDao.save(c2);
		simpleDao.save(c3);

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();
        
        
        manager.flush();
        
        
        
	}        
	

}
