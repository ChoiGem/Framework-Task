package org.tukorea.free;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tukorea.free.config.JpaConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
public class JpaTest {
	@PersistenceContext
    private EntityManager entityManager;
	private static Logger logger = LoggerFactory.getLogger(JpaTest.class);
	
	@Test
	public void testJpa() throws Exception {
		try {
			if (entityManager.isOpen()) {
	            logger.info("EntityManager: " + entityManager.toString());
	        } else {
	            logger.error("EntityManager error");
	        }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
