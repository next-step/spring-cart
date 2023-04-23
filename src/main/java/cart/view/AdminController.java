package cart.view;

import cart.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    private final ProductRepository productRepository;

    public AdminController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin";
    }
}
