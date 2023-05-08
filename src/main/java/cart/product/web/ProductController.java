package cart.product.web;

import cart.product.application.ProductService;
import cart.product.web.dto.ProductCreateRequest;
import cart.product.web.dto.ProductResponse;
import cart.product.web.dto.ProductUpdateRequest;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(ProductResponse.from(productService.findAll()));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductCreateRequest productCreateRequest) {
        return ResponseEntity.ok(ProductResponse.from(productService.create(productCreateRequest.toProductCreate())));
    }

    @PutMapping
    public ResponseEntity<ProductResponse> update(@RequestBody ProductUpdateRequest productUpdateRequest) {
        return ResponseEntity.ok(ProductResponse.from(productService.update(productUpdateRequest.toProductUpdate())));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
