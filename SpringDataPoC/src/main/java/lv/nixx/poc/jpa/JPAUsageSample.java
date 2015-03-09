package lv.nixx.poc.jpa;

import java.util.List;

import javax.persistence.*;

import lv.nixx.poc.spring.data.domain.Customer;
import lv.nixx.poc.spring.data.domain.CustomerExtension;
import lv.nixx.poc.spring.data.domain.CustomerType;

import org.junit.Test;

public class JPAUsageSample {

	@Test
	public void testShouldSaveOneCustomerType() {
		/*
		 * Для работы данного примера используется файл persistence.xml,
		 * находится в любом месте CLASSPATH METAINF
		 */
		final EntityManagerFactory factory = Persistence.createEntityManagerFactory("test.unit");
		final EntityManager entityManager = factory.createEntityManager();
		
	    final EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

	    CustomerType customerType = new CustomerType("student", "Customer is Student");
		entityManager.merge(customerType);
		
		transaction.commit();
		entityManager.close();
	}
	
	
	@Test
	public void testTestSaveCustomersAndRetrieveUsingCriteria(){
		
		final EntityManagerFactory factory = Persistence.createEntityManagerFactory("test.unit");
		final EntityManager entityManager = factory.createEntityManager();
		
	    final EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

	    CustomerType customerType = new CustomerType("student", "Customer is Student");
		entityManager.merge(customerType);

		Customer c1 = new Customer("Jack", "Bauer", customerType);
		c1.setExtension(new CustomerExtension("addtionalData1"));
		Customer c2 = new Customer("Nikolas", "Cage", customerType);
		c2.setExtension(new CustomerExtension("addtionalData2"));
		Customer c3 = new Customer("Piter", "First", null);
		c3.setExtension(new CustomerExtension("addtionalData3"));
		Customer c4 = new Customer("John", "Rembo", customerType);
		c4.setExtension(new CustomerExtension("addtionalData4"));
		
		entityManager.merge(c1);
		entityManager.merge(c2);
		entityManager.merge(c3);
		entityManager.merge(c4);
		
		transaction.commit();
		
	    Query query = entityManager.createQuery("SELECT e FROM Customer e");
	    List<Customer> resultList = (List<Customer>) query.getResultList();
	    
	    for(Customer c: resultList){
	    	System.out.println(c);
	    }
	    
		
//		  CriteriaQuery<Country> q = cb.createQuery(Country.class);
//		  Root<Country> c = q.from(Country.class);
//		  Join<Country> p = c.join("capital", JoinType.LEFT);
//		  q.multiselect(c, p.get("name"));
	}

}
