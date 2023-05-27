package cart.controller;

import cart.controller.dto.request.ProductEditRequest;
import cart.controller.dto.request.ProductRequest;
import cart.controller.dto.response.ProductResponse;
import cart.controller.dto.response.ProductsResponse;
import cart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ProductsResponse> getProducts() {
        ProductsResponse response = productService.findAll();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable @Min(1) Long productId) {
        ProductResponse response = productService.createProductResponse(productService.findById(productId));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> post(@RequestBody @Valid ProductRequest productRequest) {
        ProductResponse response = productService.save(productRequest);
        return ResponseEntity.created(URI.create("/products/" + response.getId())).body(response);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Void> edit(@PathVariable @Min(1) Long productId, @RequestBody @Valid ProductEditRequest productEditRequest) {
        productService.edit(productId, productEditRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) Long productId) {
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }
}
