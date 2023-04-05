package cart.controller;

import cart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IndexController {
    private final ProductService productService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute(
                "products",
                productService.getProducts()
                );
        return "index";
    }
}
