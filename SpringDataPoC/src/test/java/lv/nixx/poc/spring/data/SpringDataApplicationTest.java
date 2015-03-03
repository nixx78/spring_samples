package lv.nixx.poc.spring.data;

import javax.transaction.Transactional;

import lv.nixx.poc.spring.data.domain.*;
import lv.nixx.poc.spring.data.repository.*;
import lv.nixx.poc.spring.data.repository.CustomerExtensionRepository;

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
	private SimpleDAO simpleDao;
	
	@Test
	@Rollback(false)
	public void testDAO() {
		customerRepository.deleteAll();
		typeRepository.deleteAll();
		
		final CustomerType simpleCustomer = new CustomerType("simple","Simple Customer");
		final CustomerType vipCustomer = new CustomerType("vip","Simple Customer");

		// save a couple of customers
		Customer c1 = new Customer("Jack", "Bauer", simpleCustomer);
		c1.setExtension(new CustomerExtension("Jack's additional data"));

		Customer c2 = new Customer("Chloe", "O'Brian", vipCustomer);
		Customer c3 = new Customer("Nikolas", "Smith", simpleCustomer);
		c3.setExtension(new CustomerExtension("Nikolas's additional data"));

		simpleDao.save(c1);
		simpleDao.save(c2);
		simpleDao.save(c3);
		

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : simpleDao.findAll()) {
            System.out.println(customer);
        }
        System.out.println();
        
        
        
	}        

	
	
	@Test
	public void test() {
		// create and save different CustomerTypes
		final CustomerType simpleCustomer = new CustomerType("simple","Simple Customer");
		final CustomerType vipCustomer = new CustomerType("vip","Simple Customer");

		typeRepository.save(simpleCustomer);
		typeRepository.save(vipCustomer);
		
        // fetch all customer type
        System.out.println("CustomerTypes found with findAll():");
        System.out.println("-------------------------------");
        for (CustomerType type : typeRepository.findAll()) {
            System.out.println(type);
        }
        System.out.println();
		
        // save a couple of customers
        customerRepository.save(new Customer("Jack", "Bauer", simpleCustomer));
        customerRepository.save(new Customer("Chloe", "O'Brian", vipCustomer));
        customerRepository.save(new Customer("Kim", "Bauer", simpleCustomer));
        customerRepository.save(new Customer("David", "Palmer", vipCustomer));
        customerRepository.save(new Customer("Michelle", "Dessler", simpleCustomer));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer by ID
        Customer customer = customerRepository.findOne(1L);
        System.out.println("Customer found with findOne(1L):");
        System.out.println("--------------------------------");
        System.out.println(customer);
        System.out.println();

        // fetch customers by last name
        System.out.println("Customer found with findByLastName('Bauer'):");
        System.out.println("--------------------------------------------");
        for (Customer bauer : customerRepository.findByLastName("Bauer")) {
            System.out.println(bauer);
        }
      
    }
	
	

}
