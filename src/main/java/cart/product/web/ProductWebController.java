package cart.product.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProductWebController {

    private final ProductApiController productApiController;

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("products",
                productApiController.readProducts());
        return "index";
    }

    @GetMapping("/admin")
    public String showAdmin(Model model) {
        model.addAttribute("products",
                productApiController.readProducts());
        return "admin";
    }
}
