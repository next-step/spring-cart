package cart.controller;

import cart.controller.dto.response.ProductsResponse;
import cart.controller.dto.response.UsersResponse;
import cart.service.CartService;
import cart.service.ProductService;
import cart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class PageController {

    private final ProductService productService;
    private final UserService userService;
    private final CartService cartService;

    @GetMapping("/")
    public String get(Model model) {
        ProductsResponse products = productService.findAll();
        model.addAttribute("products", products.getProductResponses());
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        ProductsResponse products = productService.findAll();
        model.addAttribute("products", products.getProductResponses());
        return "admin";
    }

    @GetMapping("/settings")
    public String setting(Model model) {
        UsersResponse users = userService.findAll();
        model.addAttribute("users", users.getUserResponses());
        return "settings";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }
}
