package cart.presentation;

import cart.business.ProductService;
import cart.presentation.dto.RequestProduct;
import cart.presentation.dto.ViewProduct;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void createProduct(@RequestBody RequestProduct product) {
        productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable long id, @RequestBody RequestProduct product) {
        productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

    @GetMapping
    public List<ViewProduct> getProducts() {
        return productService.getProducts();
    }
}
