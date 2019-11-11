package org.hibernate.bugs;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM,
 * using the Java Persistence API.
 */
public class JPAUnitTestCase {
	private static final Logger LOGGER = LoggerFactory.getLogger(JPAUnitTestCase.class);

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh13721Test() throws Exception {
		long id;
		{
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			Root root = new Root();
			root.setChildCollection(Arrays.asList(new Child(root, "a", "b", "c")));
			entityManager.persist(root);
			entityManager.getTransaction().commit();
			id = root.getId();
			entityManager.close();
		}
		{
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			Root root = entityManager.find(Root.class, id);
			Assert.assertNotNull(root);
			Assert.assertNotNull(root.getChildCollection());
			LOGGER.info("{}", root.getChildCollection());
			Assert.assertEquals(1, root.getChildCollection().size());
			entityManager.getTransaction().commit();
			entityManager.close();
		}
	}
}
