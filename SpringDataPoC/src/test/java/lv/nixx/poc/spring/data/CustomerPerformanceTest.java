package lv.nixx.poc.spring.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.transaction.Transactional;

import lv.nixx.poc.spring.data.domain.*;
import lv.nixx.poc.spring.data.repository.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfiguration.class)
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class CustomerPerformanceTest {
	
	// TODO Implement integration performance tests, findAll() v.s findAllCustomers()

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TypeRepository typeRepository;
	@Autowired
	private CustomerExtensionRepository customerExtensionRepository;
	@Autowired
	private AdressRepository adressRepository;

	@Autowired
	private CustomerDAO simpleDao;

	@Autowired
	private EntityManager entityManager;
	
	@Test
	@Ignore
	public void fillCustomerData() {
		// Clean tables
		adressRepository.deleteAll();
		customerRepository.deleteAll();
		typeRepository.deleteAll();
		entityManager.flush();
		
		final CustomerType simpleCustomer = new CustomerType("simple","Simple Customer");
		typeRepository.save(simpleCustomer);
		
		final long startTime = System.currentTimeMillis();
		final int recordCount = 10000;
		
		// Create and save multiply customers
		List<Customer> customers = new ArrayList<>(recordCount);
		for (int i = 0; i < recordCount; i++) {
			customers.add(new Customer("name", "surname", simpleCustomer, new CustomerExtension("additionalData")));
		}
		customerRepository.save(customers ); 
		System.out.println("[" + recordCount + "] insertion done by [" + (System.currentTimeMillis()-startTime) + "] milleseconds");
	}
	
	@Test
	@Ignore
	public void findAllDataUsingLeftJoin() {
		final long startTime = System.currentTimeMillis();
		int recordCount = 0;
		
		for (Customer m : customerRepository.findAllCustomers() ) {
			recordCount++;
		}
		System.out.println("[" + recordCount + "] records retrieved by [" + (System.currentTimeMillis()-startTime) + "] milleseconds using LEFT JOIN");
	}

	@Test
	@Ignore
	public void findAllDataRepositoryFindAll() {
		final long startTime = System.currentTimeMillis();
		int recordCount = 0;
		
		for (Customer m : customerRepository.findAll() ) {
			recordCount++;
		}
		System.out.println("[" + recordCount + "] records retrieved by [" + (System.currentTimeMillis()-startTime) + "] milleseconds using findAll()");
	}

}

