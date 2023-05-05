package cart.controller;

import cart.auth.AuthDto;
import cart.auth.AuthPrincipal;
import cart.service.ProductService;
import cart.value.ProductRequest;
import cart.value.ProductResponse;
import cart.value.ProductsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.createProduct(productRequest);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping
    public ResponseEntity<ProductsResponse> findAll() {
        ProductsResponse productsResponse = productService.findAllProducts();
        return ResponseEntity.ok(productsResponse);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long productId, @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.updateProduct(productId, productRequest);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("/{productId}/carts")
    public ResponseEntity<ProductResponse> addCart(@PathVariable Long productId, @AuthPrincipal AuthDto authDto) {
        ProductResponse cartItemResponse = productService.addCartItem(productId, authDto.getId());
        return ResponseEntity.ok(cartItemResponse);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
