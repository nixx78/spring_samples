package lv.nixx.poc.spring.data.jpa;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import lv.nixx.poc.spring.data.domain.AdditionalData;
import lv.nixx.poc.spring.data.domain.MainData;

import org.junit.Test;

public class MainDataJPAUsageSample {

	@Test
	public void testShouldAddMainData() {
		final EntityManagerFactory factory = Persistence.createEntityManagerFactory("test.unit");
		final EntityManager entityManager = factory.createEntityManager();

		deleteAllRecords(entityManager);

		final int recordCount = 10;

		entityManager.getTransaction().begin();
		for (int i = 0; i < recordCount; i++) {
			MainData md = new MainData("mainData" + i, new AdditionalData("additionalData" + i));
			entityManager.merge(md);
		}

		entityManager.getTransaction().commit();

		Query query = entityManager.createQuery("SELECT e FROM MainData e");
		List<MainData> resultList = (List<MainData>) query.getResultList();
		System.out.println("MainData records count: " + resultList.size());

		assertEquals(10, resultList.size());

		entityManager.close();
	}

	@Test
	public void testShouldRetrieveAllMainDataUsingCriteria() {

		final EntityManagerFactory factory = Persistence.createEntityManagerFactory("test.unit");
		final EntityManager entityManager = factory.createEntityManager();

		Query query = entityManager.createQuery("SELECT m FROM MainData m LEFT JOIN FETCH m.additionalData");
		
		List<MainData> resultList = (List<MainData>) query.getResultList();
		System.out.println("MainData records count: " + resultList.size());

		for (MainData m : resultList) {
			System.out.println(m);
		}
	}

	@Test
	public void testShouldRetrieveAllMainDataUsingCriteriaBuilder() {

		final EntityManagerFactory factory = Persistence.createEntityManagerFactory("test.unit");
		final EntityManager em = factory.createEntityManager();
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		// Запрос SELECT m FROM MainData m LEFT JOIN FETCH m.additionalData можно задать также, при помощи кода, который показан ниже...
		CriteriaQuery<MainData> criteriaQuery = criteriaBuilder.createQuery(MainData.class);
		Root<MainData> mainData = criteriaQuery.from(MainData.class);
		mainData.fetch("additionalData", JoinType.LEFT);
		criteriaQuery.select(mainData);
		
		TypedQuery<MainData> query = em.createQuery(criteriaQuery);
		List<MainData> resultList = query.getResultList();
		System.out.println("MainData records count: " + resultList.size());

		for (MainData m : resultList) {
			System.out.println(m);
		}
		
		em.close();
	}

	@Test
	public void testShouldExecuteNamedQuery() {

		final EntityManagerFactory factory = Persistence.createEntityManagerFactory("test.unit");
		final EntityManager em = factory.createEntityManager();

		// TODO дополнить пример использованием параметров
		Query namedQuery = em.createNamedQuery("MainData.FindAll");
		
		List<MainData> resultList = namedQuery.getResultList();
		System.out.println("MainData records count: " + resultList.size());

		for (MainData m : resultList) {
			System.out.println(m);
		}
		
		em.close();
	}

	

	private void deleteAllRecords(final EntityManager entityManager) {
		final EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.createQuery("DELETE FROM AdditionalData").executeUpdate();
		entityManager.createQuery("DELETE FROM MainData").executeUpdate();
		transaction.commit();
	}

}
