package cart.cotroller;

import cart.model.Product;
import cart.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PutMapping("")
    ResponseEntity<Product> addProductList(@RequestBody Product product) {
        Product resultProduct = productService.addProduct(product);
        if(resultProduct != null) {
            return ResponseEntity.ok().body(resultProduct);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("{id}")
    ResponseEntity<Product> updateProductList(@PathVariable("id") String id, @RequestBody Product product) {
        Product resultProduct = productService.updateProduct(id, product);
        if(resultProduct != null) {
            return ResponseEntity.ok().body(resultProduct);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}/delete")
    ResponseEntity<Void> deleteProductList(@PathVariable("id") int id) {
        if(productService.deleteProduct(id) == true) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
