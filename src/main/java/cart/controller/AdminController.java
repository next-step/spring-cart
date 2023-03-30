package cart.controller;


import cart.Service.ProductService;
import cart.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("products", productService.productList());
        return "admin";
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Integer> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PostMapping("/removeProduct")
    public ResponseEntity<Integer> removeProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.removeProduct(product));
    }

    @PostMapping("/changeProduct")
    public ResponseEntity<Integer> changeProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.changeProduct(product));
    }

}