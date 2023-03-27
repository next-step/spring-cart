package cart.controller;

import cart.service.ProductService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin")
    public String admin(HttpServletRequest request, Model model) {
        model.addAttribute("products", productService.productList().getProducts());
        return "admin";
    }
}
