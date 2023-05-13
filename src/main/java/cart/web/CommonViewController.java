package cart.web;

import cart.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class CommonViewController {

    private final ProductService productService;

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
        // TODO 회원 목록 model에 담기
        return "settings";
    }
}
