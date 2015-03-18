package lv.nixx.poc.spring.data.mappingsamples;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.persistence.*;

import lv.nixx.poc.spring.data.mappingsamples.tableperclass.BonusedClient;
import lv.nixx.poc.spring.data.mappingsamples.tableperclass.SalaryProjectClient;
import lv.nixx.poc.spring.data.mappingsamples.tableperclass.Student;
import lv.nixx.poc.spring.data.mappingsamples.tableperclass.VisaGoldClient;

import org.junit.Test;

public class TablePerClassSample {
	
	@Test
	public void createAndSaveEntities(){
	
		final EntityManagerFactory factory = Persistence.createEntityManagerFactory("test.unit");
		final EntityManager entityManager = factory.createEntityManager();

		deleteAllRecords(entityManager);
		
		entityManager.getTransaction().begin();
		
		entityManager.merge(new Student("Ivan", "Ivanov", "This client is student", 01234567L));
		entityManager.merge(new Student("Ivan", "Petrov", "This client is student", 76543210L));
		
		entityManager.merge(new VisaGoldClient("John", "Smith", 10L, BigDecimal.valueOf(10000)));
		
		entityManager.merge(new SalaryProjectClient("Marija", "Cury", "This client is salary project client", BigDecimal.valueOf(20000)));
		entityManager.merge(new SalaryProjectClient("Ivan", "Ivan", "This client is salary project client", BigDecimal.valueOf(30000)));
		entityManager.merge(new SalaryProjectClient("John", "Rembo", "This client is salary project client", BigDecimal.valueOf(40000)));
		entityManager.merge(new SalaryProjectClient("Chack", "Norris", "This client is salary project client", BigDecimal.valueOf(50000)));
		
		entityManager.merge(new BonusedClient("Elizabeth", "Theilor", 20L, 5000L));
		entityManager.merge(new BonusedClient("Johny", "Depth", 25L, 15000L));
		entityManager.merge(new BonusedClient("Jordge", "Cluny", 30L, 51000L));
		
		entityManager.getTransaction().commit();
		
		Query allStudentQuery = entityManager.createQuery("select s from Student.TPC s", Student.class);
		assertEquals(2, allStudentQuery.getResultList().size());

		Query visaGoldQuery = entityManager.createQuery("select s from VisaGoldClient.TPC s", VisaGoldClient.class);
		assertEquals(1, visaGoldQuery.getResultList().size());

		Query bonusedClientQuery = entityManager.createQuery("select s from BonusedClient.TPC s", BonusedClient.class);
		assertEquals(3, bonusedClientQuery.getResultList().size());

		Query salaryProjectClientQuery = entityManager.createQuery("select s from SalaryProjectClient.TPC s", SalaryProjectClient.class);
		assertEquals(4, salaryProjectClientQuery.getResultList().size());

		entityManager.close();
	}
	
	private void deleteAllRecords(EntityManager entityManager) {
		entityManager.getTransaction().begin();
		
		entityManager.createQuery("delete from Student.TPC").executeUpdate();
		entityManager.createQuery("delete from VisaGoldClient.TPC").executeUpdate();
		entityManager.createQuery("delete from BonusedClient.TPC").executeUpdate();
		entityManager.createQuery("delete from SalaryProjectClient.TPC").executeUpdate();
		
		entityManager.getTransaction().commit();
	}
	

}
