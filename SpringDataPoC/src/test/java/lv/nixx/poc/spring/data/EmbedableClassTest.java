package lv.nixx.poc.spring.data;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import lv.nixx.poc.spring.data.domain.GenericPerson;
import lv.nixx.poc.spring.data.domain.PersonExtension;
import lv.nixx.poc.spring.data.repository.PersonRepository;

import org.junit.Before;
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
public class EmbedableClassTest {

	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private EntityManager entityManager;

	@Before
	public void init(){
		repository.deleteAll();
		entityManager.flush();
	}
	
	@Test
	public void createPersonsWithExtension() {
		GenericPerson p1 = new GenericPerson("Ivan", "Ivanov", new PersonExtension("line1", "line2"));
		GenericPerson p2 = new GenericPerson("John", "Smith",  new PersonExtension("line1", "line2"));
		GenericPerson p3 = new GenericPerson("Anna", "Smith", null);
		
		repository.save(p1);
		repository.save(p2);
		repository.save(p3);
		
		entityManager.flush();
		
		Iterable<GenericPerson> findAll = repository.findAll();
		for (GenericPerson person : findAll) {
			System.out.println(person);
		}


	}

}
