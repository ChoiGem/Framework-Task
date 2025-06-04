package org.tukorea.free.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tukorea.free.domain.OrderDTO;
import org.tukorea.free.domain.OrderEntity;
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
    public Integer placeOrder(OrderDTO orderDTO, List<OrderItemDTO> itemDTOList) {
    	// 주문 저장 ― PK 자동 생성
    	OrderEntity savedOrder = orderRepository.save(OrderDTO.toEntity(orderDTO));
        Integer orderId = savedOrder.getId(); // 새로 생성된 주문번호
    	
        // 아이템 저장 & 재고 차감
        for (OrderItemDTO dto : itemDTOList) {

            // FK 세팅 후 저장 
            dto.setOrderId(orderId);
            orderItemRepository.save(OrderItemDTO.toEntity(dto));

            // 재고 차감
            ProductEntity product = productRepository.findById(dto.getProductId())
                                                     .orElseThrow(() ->
                      new RuntimeException("상품이 존재하지 않습니다: " + dto.getProductId()));

            int currentStock = Integer.parseInt(product.getStock());
            int orderQty     = Integer.parseInt(dto.getQuantity());

            if (currentStock < orderQty) {
                throw new RuntimeException("재고 부족: " + product.getName());
            }

            product.setStock(String.valueOf(currentStock - orderQty));
            productRepository.save(product);
        }

        return orderId;
	}
    
    // 주문자의 주문 내역 목록 조회
	public List<OrderDTO> getOrdersByUserId(String userId) {
		return orderRepository.findByUserId(userId).stream().map(OrderDTO::toDTO).collect(Collectors.toList());
	}
    
    // 주문 내역 조회(하나)
	public OrderDTO getOrderById(Integer id) {
        return orderRepository.findById(id).map(OrderDTO::toDTO).orElse(null);
	}
}
