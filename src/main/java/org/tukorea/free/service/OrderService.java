package org.tukorea.free.service;

import java.util.List;
import org.tukorea.free.domain.OrderDTO;
import org.tukorea.free.domain.OrderItemDTO;

public interface OrderService {
    void placeOrder(OrderDTO orderDTO, List<OrderItemDTO> itemDTOList); // 주문 등록 (트랜잭션 대상)
    List<OrderDTO> getOrdersByUserId(String userId);
    OrderDTO getOrderById(String id);
}
