package org.tukorea.free.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tukorea.free.domain.ProductDTO;
import org.tukorea.free.persistence.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
    private ProductRepository productRepository;
	
	// 상품 전체 조회
	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll().stream().map(ProductDTO::toDTO).collect(Collectors.toList());
	}
	
	// 상품 상세 조회
	public ProductDTO getProductById(String id) {
		return productRepository.findById(id).map(ProductDTO::toDTO).orElse(null);
    }
}
