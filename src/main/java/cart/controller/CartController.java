package cart.controller;

import cart.domain.Product;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = new LinkedList<>();
        products.add(new Product(1L, "bbq", "images/sample.jpeg", 10000L));
        model.addAttribute("products", products);
        return "index.html";
    }
}
