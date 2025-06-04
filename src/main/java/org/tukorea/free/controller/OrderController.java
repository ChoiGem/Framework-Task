package org.tukorea.free.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
    public String showOrderForm(@RequestParam("totalPrice") String totalPrice, @RequestParam("productIds") String productIds, Model model) {
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("productIds", productIds);
        return "orderForm";
    }

	// 주문 처리
	@PostMapping("/submit")
    public void submitOrder(@ModelAttribute OrderDTO orderDTO, @RequestParam("productIds") String productIds,
    		@RequestParam("quantities") String quantities, HttpServletRequest  request,
            HttpServletResponse response) throws IOException {

        // 필수값 추가
        if (orderDTO.getOrderDate() == null) {
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            orderDTO.setOrderDate(now);
        }

        // 사용자 정보 update
        userService.updateUser(UserDTO.builder().id(orderDTO.getUserId()).name(orderDTO.getUserName())
        		.email(orderDTO.getEmail()).address(orderDTO.getAddress()).build());

        // 주문 아이템 DTO 리스트 작성
        String[] idArr  = productIds.split(",");
        String[] qtyArr = quantities.split(",");

        List<OrderItemDTO> itemList = new ArrayList<>();
        for (int i = 0; i < idArr.length; i++) {
            ProductDTO p = productService.getProductById(idArr[i]);

            itemList.add(OrderItemDTO.builder().productId(idArr[i]).quantity(qtyArr[i]).price(p.getPrice()).build());
        }

        // 주문 저장 & PK 획득
        Integer newOrderId = orderService.placeOrder(orderDTO, itemList);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String ctx = request.getContextPath();
        String home = ctx + "/"; // 이동할 주소

        out.println("<script>");
        out.println("alert('주문이 완료되었습니다.');");
        out.println("location.href='" + home + "';");
        out.println("</script>");
        out.flush();
    }

	// 주문 검색 폼
	@GetMapping("/search")
	public String searchOrderForm() {
		return "orderSearch";
	}

	// 주문 내역 출력
	@PostMapping("/list")
	public String showOrders(@RequestParam("userId") String userId, Model model,
	                         HttpServletResponse response, HttpServletRequest request) throws IOException {
	    List<OrderDTO> orderList = orderService.getOrdersByUserId(userId);

	    if (orderList.isEmpty()) {
	        response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        String contextPath = request.getContextPath(); // 여기 추가!
	        out.println("<script>alert('해당 ID로 주문한 내역이 없습니다.'); location.href='" + contextPath + "/order/search';</script>");
	        out.flush();
	        return null;
	    }

	    model.addAttribute("orderList", orderList);
	    return "orderList";
	}

}
