package org.tukorea.free.service;

import java.util.List;
import org.tukorea.free.domain.OrderDTO;
import org.tukorea.free.domain.OrderItemDTO;

public interface OrderService {
	// 주문 + 아이템 + 재고 처리
	public void placeOrder(OrderDTO orderDTO, List<OrderItemDTO> itemDTOList); // 주문 등록 (트랜잭션 대상)
    
    // 주문자의 주문 내역 목록 조회
	public List<OrderDTO> getOrdersByUserId(String userId);
    
    // 주문 내역 조회(하나)
	public OrderDTO getOrderById(String id);
}
