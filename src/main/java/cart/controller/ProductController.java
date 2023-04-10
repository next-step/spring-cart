package cart.controller;

import cart.domain.Product;
import cart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product/create")
    ResponseEntity<String> insertProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok().body("ok");
    }

    @DeleteMapping("/product/delete")
    ResponseEntity<String> deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().body("ok");
    }

    @PutMapping("/product/update")
    ResponseEntity<String> updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return ResponseEntity.ok().body("ok");
    }
}
