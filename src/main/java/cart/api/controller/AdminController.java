package cart.api.controller;

import cart.api.dto.ProductCreateRequest;
import cart.domain.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("products", productService.getAll());
        return "admin";
    }

    @PostMapping
    public String create(Model model, @RequestBody @Valid ProductCreateRequest data) {
        productService.save(data.toModel());
        model.addAttribute("products", productService.getAll());
        return "admin";
    }

    @PutMapping
    public String update(Model model) {
        model.addAttribute("products", productService.getAll());
        return "admin";
    }

    @DeleteMapping
    public String delete(Model model) {
        model.addAttribute("products", productService.getAll());
        return "admin";
    }
}
