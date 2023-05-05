package cart.controller;

import cart.application.ProductService;
import cart.controller.dto.ProductsResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private final ProductService productService;

    public PageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        ProductsResponse products = productService.getAllProducts();
        model.addAttribute("products", products.getProducts());

        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        ProductsResponse products = productService.getAllProducts();
        model.addAttribute("products", products.getProducts());

        return "admin";
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        // TODO step2에서 진행

        return "settings";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        // TODO step2에서 진행

        return "cart";
    }

}
