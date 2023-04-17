package cart.presentation.api.product;

import cart.domain.product.ProductService;
import cart.domain.product.model.ProductModel;
import cart.presentation.api.product.request.CreateProductRequest;
import cart.presentation.api.product.request.UpdateProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductApiController {

    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody CreateProductRequest request) {
        productService.save(new ProductModel(request.getName(), request.getImageUrl(), request.getPrice()));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest request) {
        productService.update(id, new ProductModel(request.getName(), request.getImageUrl(), request.getPrice()));
        return ResponseEntity.ok().build();
    }
}
