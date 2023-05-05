package cart.presentation;

import cart.presentation.dto.ProductCreateRequest;
import cart.presentation.dto.ProductDetailResponse;
import cart.presentation.dto.ProductUpdateRequest;
import cart.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDetailResponse>> allProducts() {
        List<ProductDetailResponse> response = this.productService.allProducts();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Long> registerProduct(@RequestBody ProductCreateRequest request) {
        Long savedProductId = this.productService.registerProduct(request);
        return ResponseEntity.ok(savedProductId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequest request) {
        this.productService.updateProduct(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
