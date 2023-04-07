package cart.controller;

import cart.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AdminController {
    ProductService productService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute(
                "products",
                productService.getProducts()
        );
        return "/admin";
    }

}
