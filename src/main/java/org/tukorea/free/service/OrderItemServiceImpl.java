package org.tukorea.free.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tukorea.free.domain.OrderItemDTO;
import org.tukorea.free.persistence.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
    private OrderItemRepository orderItemRepository;
	
	public List<OrderItemDTO> getItemsByOrderId(String orderId) {
        return orderItemRepository.findByOrderId(orderId).stream().map(OrderItemDTO::toDTO).collect(Collectors.toList());
    }
}
