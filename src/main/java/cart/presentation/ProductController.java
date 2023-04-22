package cart.presentation;

import cart.presentation.dto.ProductDetailResponse;
import cart.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDetailResponse>> products() {
        List<ProductDetailResponse> response = this.productService.allProducts();
        return ResponseEntity.ok(response);
    }
}
