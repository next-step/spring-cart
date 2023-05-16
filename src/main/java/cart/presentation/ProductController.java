package cart.presentation;

import cart.application.ProductService;
import cart.application.dto.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping("/products")
  public ResponseEntity register(@RequestBody CreateProductRequest request) {
    Long productId = this.productService.register(request);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(productId)
        .toUri();

    return ResponseEntity.created(location).build();
  }
}
