package org.tukorea.free.service;

import java.util.List;
import org.tukorea.free.domain.OrderItemDTO;

public interface OrderItemService {
	// 주문 상세 조회
	public List<OrderItemDTO> getItemsByOrderId(String orderId);
}
