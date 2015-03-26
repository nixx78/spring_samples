package lv.nixx.poc.spring.data.jpa;

import static org.junit.Assert.*;

import java.util.*;
import javax.persistence.*;
import lv.nixx.poc.spring.data.domain.Person;
import org.junit.*;

public class JPATransactionIsolationSample {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("test.unit");
	
	@Before
	public void init() {
		// Данные настройки работают только если используется EmbeddedDerby
		System.setProperty("derby.locks.deadlockTimeout","2");
		System.setProperty("derby.locks.waitTimeout","2");
		
		// http://db.apache.org/derby/docs/10.5/ref/rrefproperrowlocking.html#rrefproperrowlocking
		//System.setProperty("derby.storage.rowLocking", "true");

		EntityManager entityManager = emf.createEntityManager();

		final EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// Данные удаляем именно из таблиц, ибо, если удалять из сущности Person
		// получаем constrain error
		entityManager.createNativeQuery("DELETE FROM Task").executeUpdate();
		entityManager.createNativeQuery("DELETE FROM Aliase").executeUpdate();
		entityManager.createNativeQuery("DELETE FROM PersonAdditionalField").executeUpdate();
		entityManager.createNativeQuery("DELETE FROM Person").executeUpdate();
		transaction.commit();

		entityManager.close();
	}
	
	@Test
	public void dirtyReadTransactionIsolation(){
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
			Person pers = new Person("Name1" + new Date(), "Surname1", null);
			Long id = em.merge(pers).getId();
			System.out.println("created person id: " + id);
		
			try {
				tryToSelectAllRecords(id);
			} catch (Exception ex){
				// тут мы получаем ошибку, из-за того, что стоит лок на все таблицу а не 
				// на конкретную запись, это зависит от реализации базы данных
				System.err.println(ex);
			}
			
		em.getTransaction().commit();
	}
	
	private void tryToSelectAllRecords(Long id) {
		EntityManager em1 = emf.createEntityManager();
		
	    Query query = em1.createQuery("SELECT p FROM Person p");
	    List<Person> resultList = (List<Person>) query.getResultList();
	    for(Person p: resultList){
	    	System.out.println(p);
	    }
	}


}
