package cart.controller;

import cart.domain.Product;
import cart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class CartController {

    private final ProductService productService;

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
