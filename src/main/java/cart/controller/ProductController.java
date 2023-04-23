package cart.controller;

import cart.domain.Product;
import cart.domain.Products;
import cart.dto.ProductRequest;
import cart.dto.ProductResponse;
import cart.exception.ProductException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final Products products;

    public ProductController(Products products) {
        this.products = products;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest product) {
        final Product saved = products.add(product.toEntity());
        return ResponseEntity.ok(ProductResponse.of(saved));
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductResponse>> readAll() {
        final List<Product> productsAll = products.getAll();
        return ResponseEntity.ok(ProductResponse.listOf(productsAll));
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest request) {
        final Product saved = products.add(request.toEntity(id));
        return ResponseEntity.ok(ProductResponse.of(saved));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        final Product product = products.findById(id).orElseThrow(() -> new ProductException("존재하지 않는 상품입니다."));
        products.remove(product);
        return ResponseEntity.noContent().build();
    }
}
