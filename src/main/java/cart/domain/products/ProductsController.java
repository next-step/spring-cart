package cart.domain.products;

import cart.domain.global.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductsController {

    private final List<Product> products;

    @Autowired
    public ProductsController(List<Product> products){
        this.products = products;
    }

    @GetMapping("/")
    public String get(Model model) {
        model.addAttribute("products", products);
        return "index";
    }

}
