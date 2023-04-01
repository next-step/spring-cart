package cart.controller;

import cart.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final ProductService productService;

    @GetMapping(value = "/admin")
    public String admin(Model model) {
        model.addAttribute("products", productService.getList());
        return "/admin";
    }
}
