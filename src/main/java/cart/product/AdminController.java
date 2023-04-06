package cart.product;

import cart.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;

    @GetMapping("/admin")
    public String adminProductList(Model model) {

        var products = productService.findByAll();

        model.addAttribute("products", products);

        return "/admin";
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {

        var result = productService.createProduct(product);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {

        var result = productService.createProduct(product);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
        return "admin";
    }
}
