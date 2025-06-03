package org.tukorea.free.service;

import java.util.List;
import org.tukorea.free.domain.ProductDTO;

public interface ProductService {
	List<ProductDTO> getAllProducts();
    ProductDTO getProductById(String id);
}
