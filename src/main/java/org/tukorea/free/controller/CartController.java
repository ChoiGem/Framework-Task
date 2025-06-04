package org.tukorea.free.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String showCartPage() {
        // 실제 상품 목록은 JavaScript(localStorage)로 렌더링됨
        return "cart";
    }
}
