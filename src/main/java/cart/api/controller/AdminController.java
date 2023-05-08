package cart.api.controller;

import cart.api.dto.ProductRequest;
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
    public String create(Model model, @RequestBody @Valid ProductRequest data) {
        productService.save(data.toModel());
        model.addAttribute("products", productService.getAll());
        return "admin";
    }

    @PutMapping("{id}")
    public String update(Model model, @PathVariable Long id, @RequestBody ProductRequest data) {
        productService.update(id, data.toModel());
        model.addAttribute("products", productService.getAll());
        return "admin";
    }

    @DeleteMapping("{id}")
    public String delete(Model model, @PathVariable Long id) {
        productService.delete(id);
        model.addAttribute("products", productService.getAll());
        return "admin";
    }
}
