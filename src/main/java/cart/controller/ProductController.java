package cart.controller;


import cart.model.Product;
import cart.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> create(@RequestBody Product product) {
        return ResponseEntity.ok().body(productService.insert(product));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Product> create(@PathVariable int id) {
        return ResponseEntity.ok().body(productService.product(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Integer> update(@RequestBody Product product) {
        System.out.println(product.getId());
        return ResponseEntity.ok().body(productService.update(product));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Integer> delete(@PathVariable int id) {
        return ResponseEntity.ok().body(productService.delete(id));
    }

}
