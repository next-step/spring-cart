package cart.controller;

import cart.domain.Product;
import cart.dto.ProductCreateDto;
import cart.dto.ProductUpdateDto;
import cart.service.ProductService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/products")
  public ResponseEntity<List<Product>> getAll() {
    List<Product> products = productService.getAll();
    return ResponseEntity.ok(products);
  }

  @PutMapping(value = "/products/{id}")
  public void updateProduct(@PathVariable Long id,
      @RequestBody ProductUpdateDto updateDto) {
    productService.findById(id);
    productService.updateProduct(updateDto);
  }

  @DeleteMapping("/products/{id}")
  public void deleteProduct(@PathVariable Long id) {
    productService.delete(id);
  }

  @PostMapping("/products")
  public void addProduct(@RequestBody ProductCreateDto createDto) {
    productService.addProduct(createDto);
  }
}
