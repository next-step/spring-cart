package cart.admin.controller;

import cart.product.dto.request.ProductRequest;
import cart.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {
    private final ProductService productService;
    @GetMapping("")
    public String findAll(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin";
    }

    @PostMapping("/create-product")
    public String insertProduct(Model model, @RequestBody ProductRequest productRequest) {
        productService.insert(productRequest);
        model.addAttribute("products", productService.findAll()); // 최신화 시켜주기
        return "admin";
    }

    @PutMapping("/update-product/{id}")
    public String updateProduct(Model model, @PathVariable Long id, @RequestBody ProductRequest productRequest) {
        productService.update(id, productRequest);
        model.addAttribute("products", productService.findAll()); // 최신화 시켜주기
        return "admin";
    }

    @DeleteMapping("/delete-product/{id}")
    public String deleteProduct(Model model, @PathVariable Long id) {
        productService.delete(id);
        model.addAttribute("products", productService.findAll()); // 최신화 시켜주기
        return "admin";
    }


}
