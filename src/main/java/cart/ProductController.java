package cart;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/products")
  public ResponseEntity<List<Product>> getAll(){
    List<Product> products = productService.getAll();
    return ResponseEntity.ok(products);
  }

//  @PostMapping("/products/{productId}")
//  public ResponseEntity<Void> editProduct(@PathVariable Long productId){
////    productService.update()
//    return "";
//  }
}
