package cart.product.controller;

import cart.product.application.ProductService;
import cart.product.controller.dto.ProductRequest;
import cart.product.controller.dto.ProductResponse;
import cart.product.controller.dto.ProductsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductApiController {

    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        ProductResponse response = productService.createProduct(request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable long productId,
                                                         @RequestBody ProductRequest request) {
        ProductResponse response = productService.updateProduct(productId, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ProductsResponse> findProduct() {
        ProductsResponse response = productService.getAllProducts();

        return ResponseEntity.ok(response);
    }

}
