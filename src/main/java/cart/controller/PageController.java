package cart.controller;

import cart.controller.dto.ProductsResponse;
import cart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class PageController {

    private final ProductService productService;

    @GetMapping("/")
    public String get(Model model) {
        ProductsResponse products = productService.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        ProductsResponse products = productService.findAll();
        model.addAttribute("products", products);
        return "admin";
    }
}
