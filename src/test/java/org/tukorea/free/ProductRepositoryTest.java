package org.tukorea.free;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tukorea.free.config.RootConfig;
import org.tukorea.free.domain.ProductEntity;
import org.tukorea.free.persistence.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class ProductRepositoryTest {
	@Autowired
	private ProductRepository productRepository;
	private static Logger logger = LoggerFactory.getLogger(ProductRepositoryTest.class);

	@Test
	public void testSaveAndFindAndDelete() throws Exception {
		try {
			ProductEntity product = new ProductEntity("test01", "테스트상품", "10000", "5", "test.png");

			productRepository.save(product);
			ProductEntity result = productRepository.findById("test01").orElse(null);

			logger.info("result: " + result.toString());
			logger.info("product_name: " + result.getName());

			productRepository.deleteById("test01");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
