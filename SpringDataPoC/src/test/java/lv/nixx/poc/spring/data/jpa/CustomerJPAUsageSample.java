package lv.nixx.poc.spring.data.jpa;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.*;

import lv.nixx.poc.spring.data.domain.*;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

/*
 * Для работы данного примера используется файл persistence.xml,
 * находится в любом месте CLASSPATH META-INF
 */
public class CustomerJPAUsageSample {

	final EntityManagerFactory factory = Persistence.createEntityManagerFactory("test.unit");

	@Before
	public void cleanTables(){
		final EntityManager entityManager = factory.createEntityManager();
		final EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.createQuery("DELETE FROM Customer").executeUpdate();
		transaction.commit();
		entityManager.close();
	}
	
	@Test
	public void testShouldPersistCustomer(){
		final EntityManager entityManager = factory.createEntityManager();
	    final EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Customer customer = new Customer("name1", "surname1", null);
		entityManager.persist(customer);
		
		assertNotNull(customer.getId()); // проверяем, что ID сгенерировано для Customer
		transaction.commit();
	}
	
	@Test(expected = PersistenceException.class)
	public void testShouldTryToSaveCustomerWithNullNameAndGetException() {
		final EntityManager entityManager = factory.createEntityManager();
		
	    final EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(new Customer(null, null, null));
	}
	
	@Test
	public void testTestSaveCustomersAndRetrieveUsingCriteria(){
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
	    
	    assertEquals("Customer count", 4, resultList.size());
	    
	    for(Customer c: resultList){
	    	System.out.println(c);
	    }
	}
	
	@Test
	public void testShouldFindCustomer(){
		final EntityManager entityManager = factory.createEntityManager();
		
	    final EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		Customer c1 = new Customer("Jack", "Bauer", null);
		Customer c2 = new Customer("Nikolas", "Cage", null);
		
		c1 = entityManager.merge(c1);
		c2 = entityManager.merge(c2);
		
		transaction.commit();
		
		Long c1Id = c1.getId();
		Long c2Id = c2.getId();
		
		Customer foundC1 = entityManager.find(Customer.class, c1Id);
		Customer foundC2 = entityManager.find(Customer.class, c2Id);
		
		assertEquals(c1.getId(), foundC1.getId());
		assertEquals(c2.getId(), foundC2.getId());
		
		entityManager.close();
	}
	
	@Test
	public void testShouldShowHowWorkWithQueries(){
		final EntityManager entityManager = factory.createEntityManager();
		
	    final EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

	    CustomerType customerType = new CustomerType("student", "Customer is Student");
		entityManager.merge(customerType);

		Customer c1 = new Customer("Jack", "Bauer", customerType);
		c1.setExtension(new CustomerExtension("addtionalData1"));
		c1.addAdress(new Adress("1_line1", "1_line2"));
		c1.addAdress(new Adress("2_line1", "2_line2"));
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

		// Мы можем вызвать именованый запрос и даже передать в него параметры
		TypedQuery<Customer> query = entityManager.createNamedQuery("Customer.findCustomerByLastName", Customer.class);
		query.setParameter("lastName", "Rembo");
		List<Customer> resultList = query.getResultList();
		Customer singleResult = query.getSingleResult();
		assertNotNull(singleResult);
		
		System.out.println(resultList.get(0));

		// Запрос мы можем написать простым текстом
		query = entityManager.createQuery("Select distinct c from Customer c "
				+ "left join fetch c.extension "
				+ "left join fetch c.type "
				+ "left join fetch c.adress", Customer.class);
		
		resultList = query.getResultList();
		System.out.println("Customer records count: " + resultList.size());
		assertEquals("Customer count", 4, resultList.size());
		
		for (Customer m : resultList) {
			System.out.println(m);
		}

		// Запрос можно задать также, при помощи criteriaBuilder который показан ниже...
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
		criteriaQuery.distinct(true);
		Root<Customer> customer = criteriaQuery.from(Customer.class);
		customer.fetch("extension", JoinType.LEFT);
		customer.fetch("type", JoinType.LEFT);
		customer.fetch("adress", JoinType.LEFT);
		criteriaQuery.select(customer);
		
		query = entityManager.createQuery(criteriaQuery);
		resultList = query.getResultList();
		assertEquals("Customer count", 4, resultList.size());
		
		System.out.println("Customer records count: " + resultList.size());
		for (Customer m : resultList) {
			System.out.println(m);
		}
	
	}

}
