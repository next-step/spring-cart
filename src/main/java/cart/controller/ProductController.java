package cart.controller;

import cart.controller.dto.ProductEditRequest;
import cart.controller.dto.ProductRequest;
import cart.controller.dto.ProductResponse;
import cart.controller.dto.ProductsResponse;
import cart.domain.Product;
import cart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ProductsResponse> getProducts() {
        List<Product> products = productService.findAll();
        ProductsResponse response = ProductsResponse.of(products.stream().map(product -> ProductResponse.of(product)).collect(Collectors.toList()));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId) {
        ProductResponse response = productService.findById(productId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> post(@RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.save(productRequest);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Void> edit(@PathVariable Long productId, @RequestBody ProductEditRequest productEditRequest) {
        productService.edit(productId, productEditRequest);
        return ResponseEntity.ok().build();
    }
}
