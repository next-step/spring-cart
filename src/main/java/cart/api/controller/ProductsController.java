package cart.api.controller;

import cart.domain.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/", "/products"})
    public String home(Model model) {
        model.addAttribute("products", productService.getAll());
        return "index";
    }
}
