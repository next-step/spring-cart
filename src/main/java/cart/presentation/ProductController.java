package cart.presentation;

import cart.application.ProductService;
import cart.application.dto.FindProductResponse;
import cart.application.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping("/products")
  public ResponseEntity register(@RequestBody ProductRequest request) {
    Long productId = this.productService.register(request);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(productId)
            .toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping("/products")
  public ResponseEntity<List<FindProductResponse>> getAllProducts() {
    List<FindProductResponse> response = this.productService.getAllProducts();

    return ResponseEntity.ok(response);
  }

  @PutMapping("/products/{id}")
  public ResponseEntity<FindProductResponse> modifyProduct(
      @PathVariable("id") Long id, @RequestBody ProductRequest request) {
    FindProductResponse response = this.productService.modifyProduct(id, request);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity removeProduct(@PathVariable("id") Long id) {
    this.productService.removeProduct(id);
    return ResponseEntity.ok().build();
  }
}
