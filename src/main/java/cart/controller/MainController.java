package cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cart.dto.ProductsResponseDto;
import cart.service.ProductService;

@Controller
public class MainController {
	private final ProductService productService;

	public MainController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/")
	public String home(Model model) {
		ProductsResponseDto productsResponseDto = productService.findAll();
		model.addAttribute("products", productsResponseDto.getProducts());
		return "index";
	}

	@GetMapping("/admin")
	public String admin(Model model) {
		ProductsResponseDto productsResponseDto = productService.findAll();
		model.addAttribute("products", productsResponseDto.getProducts());
		return "admin";
	}

	@GetMapping("/settings")
	public String settings(Model model) {
		return "settings";
	}

	@GetMapping("/cart")
	public String cart(Model model) {
		return "cart";
	}
}
