package lv.nixx.poc.spring.data;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import lv.nixx.poc.spring.data.domain.AdditionalField;
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
		p1.addAliase("alias1");
		p1.addAliase("alias2");
		p1.addAliase("alias3");
		
		GenericPerson p2 = new GenericPerson("John", "Smith",  new PersonExtension("line1", "line2"));
		p2.addAdditionalField(new AdditionalField("p2_f11", "p2_f12"));
		p2.addAdditionalField(new AdditionalField("p2_f21", "p2_f22"));
		p2.addAdditionalField(new AdditionalField("p2_f31", "p2_f32"));
		
		GenericPerson p3 = new GenericPerson("Anna", "Smith", null);
		p3.addAdditionalField(new AdditionalField("p3_f11", "p3_f12"));
		p3.addAdditionalField(new AdditionalField("p3_f21", "p3_f22"));
		
		repository.save(p1);
		repository.save(p2);
		repository.save(p3);
		
		entityManager.flush();
		
		Iterable<GenericPerson> findAll = repository.findAll();
		for (GenericPerson person : findAll) {
			System.out.println(person);
			System.out.println("\t\t Aliases:" + person.getAliases());
			System.out.println("\t\t Fields:" +  person.getAdditionalFields());
		}
		
		Long p1Id = p1.getId();
		Long p2Id = p2.getId();
		Long p3Id = p3.getId();
		
		p1 = repository.findOne(p1Id);
		assertNotNull(p1);
		assertNotNull(p1.getExtension());

		p2 = repository.findOne(p2Id);
		assertNotNull(p2);
		assertNotNull(p2.getExtension());

		p3 = repository.findOne(p3Id);
		assertNotNull(p3);
		assertNull(p3.getExtension());
		
		repository.delete(p3Id);
	}

}
