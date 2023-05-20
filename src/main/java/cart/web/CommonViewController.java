package cart.web;

import cart.service.product.ProductService;
import cart.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class CommonViewController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productService.findAll());

        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("products", productService.findAll());

        return "admin";
    }

    @GetMapping("/settings")
    public String setting(Model model) {
        model.addAttribute("users", userService.findAll());

        return "settings";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }
}
