package cart.controller;

import cart.domain.Product;
import cart.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductApiController {

    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/product")
    public ResponseEntity<Void> updateProduct(@RequestBody Product product) {
        productService.update(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/product")
    public ResponseEntity<Void> updateProduct(@RequestBody long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
