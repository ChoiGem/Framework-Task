package org.tukorea.free;

import java.sql.Connection;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tukorea.free.config.RootConfig;
import org.tukorea.free.DataSourceTest;

import jakarta.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class DataSourceTest {
	@Inject
	private DataSource ds;
	
	private static Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
	
	@Test
	public void testConntection() throws Exception {
		try(Connection con = ds.getConnection()) {
			logger.info("con.toString(): "+con.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
