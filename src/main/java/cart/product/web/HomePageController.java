package cart.product.web;

import cart.product.application.ProductService;
import cart.product.web.dto.HomePageResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomePageController {

    private final ProductService productService;

    public HomePageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showHomePage(Model model) {
        model.addAttribute("products", HomePageResponse.from(productService.findAll()));
        return "index";
    }
}
