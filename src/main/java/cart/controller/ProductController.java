package cart.controller;

import cart.domain.Product;
import cart.domain.Products;
import cart.dto.ProductRequest;
import cart.dto.ProductResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    private final Products products = new Products();

    @ResponseBody
    @PostMapping("/product")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest product) {
        final Product saved = products.add(product.toEntity());
        return ResponseEntity.ok(ProductResponse.of(saved));
    }

    @ResponseBody
    @GetMapping("/product")
    public ResponseEntity<List<ProductResponse>> readAll() {
        final List<Product> productsAll = products.getAll();
        return ResponseEntity.ok(ProductResponse.listOf(productsAll));
    }

    @ResponseBody
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponse> edit(@PathVariable Long id, @RequestBody ProductRequest request) {
        final Product saved = products.add(request.toEntity(id));
        return ResponseEntity.ok(ProductResponse.of(saved));
    }

    @ResponseBody
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        final Product product = products.findById(id).orElseThrow();
        products.remove(product);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", products.getAll());
        return "index.html";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("products", products.getAll());
        return "admin.html";
    }
}
