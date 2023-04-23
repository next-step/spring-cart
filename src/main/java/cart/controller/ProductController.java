package cart.controller;

import cart.domain.Cart;
import cart.domain.Carts;
import cart.domain.Product;
import cart.domain.Products;
import cart.dto.ProductRequest;
import cart.dto.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final Products products;
    private final Carts carts;

    public ProductController(Products products, Carts carts) {
        this.products = products;
        this.carts = carts;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest) {
        final Product product = products.add(productRequest.toEntity());
        return ResponseEntity.ok(ProductResponse.of(product));
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductResponse>> readAll() {
        final List<Product> productsAll = products.getAll();
        return ResponseEntity.ok(ProductResponse.listOf(productsAll));
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        final Product product = products.add(productRequest.toEntity(id));
        return ResponseEntity.ok(ProductResponse.of(product));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        final Product product = products.findById(id).orElseThrow();
        products.remove(product);

        final List<Cart> cartsByProduct = carts.findAllByProduct(product);
        carts.removeAll(cartsByProduct);

        return ResponseEntity.noContent().build();
    }
}
