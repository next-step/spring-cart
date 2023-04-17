package cart.presentation.view.home;

import cart.domain.product.ProductService;
import cart.presentation.view.home.response.HomeProductResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", HomeProductResponse.from(productService.productList()));
        return "index";
    }
}
