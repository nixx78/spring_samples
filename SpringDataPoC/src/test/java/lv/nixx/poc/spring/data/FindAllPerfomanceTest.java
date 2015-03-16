package lv.nixx.poc.spring.data;

import static org.junit.Assert.*;

import javax.persistence.*;
import javax.transaction.Transactional;

import lv.nixx.poc.spring.data.domain.*;
import lv.nixx.poc.spring.data.repository.*;

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
@SuppressWarnings("unused")
public class FindAllPerfomanceTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private MainDataRepository mainDataRepository;

	@Test
	@Ignore
	public void fillData() {
		final int recordCount = 10000;

		mainDataRepository.deleteAll();
		entityManager.flush();

		final long startTime = System.currentTimeMillis(); 
		for (int i = 0; i < recordCount; i++) {
			mainDataRepository.save(new MainData("mainData" + i, new AdditionalData("additionalData" + i)));
		}
		entityManager.flush();
		assertEquals(recordCount, mainDataRepository.count());
		System.out.println("[" + recordCount + "] insertion done by [" + (System.currentTimeMillis()-startTime) + "] milleseconds");
	}

	@Test
	@Ignore
	public void findAllDataUsingLeftJoin() {

		final long startTime = System.currentTimeMillis();
		int recordCount = 0;
		
		for (MainData m : mainDataRepository.findAllMainData()) {
			recordCount++;
		}
		System.out.println("[" + recordCount + "] records retrieved by [" + (System.currentTimeMillis()-startTime) + "] milleseconds using LEFT JOIN");
	}
	
	
	@Test
	@Ignore
	public void findAllDataUsingRepositoryFindAll() {
		final long startTime = System.currentTimeMillis();
		int recordCount = 0;
		
		for (MainData m : mainDataRepository.findAll()) {
			recordCount++;
		}
		System.out.println("[" + recordCount + "] records retrieved by [" + (System.currentTimeMillis()-startTime) + "] milleseconds using findAll()");
	}


}
