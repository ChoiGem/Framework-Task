package org.tukorea.free.service;

import java.util.List;
import org.tukorea.free.domain.OrderItemDTO;

public interface OrderItemService {
	List<OrderItemDTO> getItemsByOrderId(String orderId);
}
