package cart.product.controller;

import cart.product.service.ProductService;
import cart.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequiredArgsConstructor
@RequestMapping("/products")
@Controller
public class ProductController {
    private final ProductService productService;
    private final AuthService authService;
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";
    }
}
