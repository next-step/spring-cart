package cart.controller;

import cart.domain.Product;
import cart.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String index(Model model) {

        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {

        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "admin";
    }
}
