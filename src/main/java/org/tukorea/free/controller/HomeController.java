package org.tukorea.free.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tukorea.free.service.ProductService;

@Controller
public class HomeController {
	@Autowired
    private ProductService productService;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping({"/", "/home"})
	 public String home(Locale locale, Model model) {
		 logger.info("Welcome home! The client locale is {}.", locale);
		 
		 model.addAttribute("products", productService.getAllProducts());
		 return "home";
	}
}
