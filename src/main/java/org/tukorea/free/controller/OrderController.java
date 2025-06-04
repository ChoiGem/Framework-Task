package org.tukorea.free.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tukorea.free.domain.OrderDTO;
import org.tukorea.free.domain.OrderItemDTO;
import org.tukorea.free.domain.ProductDTO;
import org.tukorea.free.domain.UserDTO;
import org.tukorea.free.service.OrderService;
import org.tukorea.free.service.ProductService;
import org.tukorea.free.service.UserService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;

	// 주문자 정보 입력 폼
	@GetMapping("/form")
	public String showOrderForm(@RequestParam String totalPrice, @RequestParam String productIds, Model model) {
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("productIds", productIds);
		return "orderForm";
	}

	// 주문 처리
	@PostMapping("/submit")
	public String submitOrder(@ModelAttribute OrderDTO orderDTO, @RequestParam String productIds,
			@RequestParam String quantities, Model model) {
		// 사용자 정보 추가 or 갱신
		userService.updateUser(UserDTO.builder().id(orderDTO.getUserId()).name(orderDTO.getUserName())
				.email(orderDTO.getEmail()).address(orderDTO.getAddress()).build());

		// 상품 ID 및 수량 배열로부터 OrderItemDTO 리스트 생성
		String[] idArray = productIds.split(",");
		String[] qtyArray = quantities.split(",");

		List<OrderItemDTO> itemList = new ArrayList<>();

		for (int i = 0; i < idArray.length; i++) {
			String productId = idArray[i];
			String quantity = qtyArray[i];

			// 주문 당시 상품 가격을 가져옴
			ProductDTO product = productService.getProductById(productId);
			String price = product.getPrice();

			OrderItemDTO item = OrderItemDTO.builder().id("oi" + System.currentTimeMillis() + i) // 간단한 고유 ID
					.orderId(orderDTO.getId()).productId(productId).quantity(quantity).price(price).build();

			itemList.add(item);
		}
		// OrderService에 주문 + 주문아이템 저장 위임
		orderService.placeOrder(orderDTO, itemList);

		model.addAttribute("orderId", orderDTO.getId());
		return "orderResult";
	}

	// 주문 검색 폼
	@GetMapping("/search")
	public String searchOrderForm() {
		return "orderSearch";
	}

	// 주문 내역 출력
	@PostMapping("/list")
	public String showOrders(@RequestParam String userId, Model model) {
		List<OrderDTO> orderList = orderService.getOrdersByUserId(userId);
		model.addAttribute("orderList", orderList);
		return "orderList";
	}
}
