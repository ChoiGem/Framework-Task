package org.tukorea.free.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tukorea.free.domain.OrderDTO;
import org.tukorea.free.domain.OrderItemDTO;
import org.tukorea.free.domain.ProductEntity;
import org.tukorea.free.persistence.OrderItemRepository;
import org.tukorea.free.persistence.OrderRepository;
import org.tukorea.free.persistence.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;
	
	// 주문 + 아이템 + 재고 처리 (트랜젝션 적용)
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,timeout = 10,rollbackFor = { Exception.class })
	public void placeOrder(OrderDTO orderDTO, List<OrderItemDTO> itemDTOList) {
    	// 주문 저장
        orderRepository.save(OrderDTO.toEntity(orderDTO));

        for (OrderItemDTO itemDTO : itemDTOList) {
            // 주문 상세 저장
            orderItemRepository.save(OrderItemDTO.toEntity(itemDTO));

            // 재고 차감 처리
            ProductEntity product = productRepository.findById(itemDTO.getProductId()).get();
            int currentStock = Integer.parseInt(product.getStock());
            int orderQty = Integer.parseInt(itemDTO.getQuantity());

            if (currentStock < orderQty) {
                throw new RuntimeException("재고 부족: " + product.getName());
            }

            product.setStock(String.valueOf(currentStock - orderQty));
            productRepository.save(product);
        }
	}
    
    // 주문자의 주문 내역 목록 조회
	public List<OrderDTO> getOrdersByUserId(String userId) {
		return orderRepository.findByUserId(userId).stream().map(OrderDTO::toDTO).collect(Collectors.toList());
	}
    
    // 주문 내역 조회(하나)
	public OrderDTO getOrderById(String id) {
        return orderRepository.findById(id).map(OrderDTO::toDTO).orElse(null);
	}
}
