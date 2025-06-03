package org.tukorea.free.service;

import java.util.List;
import org.tukorea.free.domain.ProductDTO;

public interface ProductService {
	// 상품 전체 조회
	public List<ProductDTO> getAllProducts();
	
	// 상품 상세 조회
	public ProductDTO getProductById(String id);
}
