package org.tukorea.free;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tukorea.free.config.RootConfig;
import org.tukorea.free.service.UserService;
import org.tukorea.free.domain.UserDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class ProductServiceTest {
	@Autowired
	private UserService userService;
	private static Logger logger = LoggerFactory.getLogger(ProductServiceTest.class);

	@Test
	public void testRegisterAndRead() throws Exception {
		try {
			UserDTO user = new UserDTO("test02", "테스트서비스유저", "user@uu.uu", "정왕동");

			userService.updateUser(user);
			UserDTO getUser = userService.getUserById("test02");
			logger.info("user: " + getUser.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
