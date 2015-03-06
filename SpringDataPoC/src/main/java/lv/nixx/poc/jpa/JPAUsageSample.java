package lv.nixx.poc.jpa;

import javax.persistence.*;

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

}
