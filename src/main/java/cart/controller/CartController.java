package cart.controller;

import cart.domain.Product;
import cart.domain.Products;
import cart.dto.ProductRequest;
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
public class CartController {

    private final Products products = new Products();

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

    @ResponseBody
    @PostMapping("/product")
    public ResponseEntity<Product> create(@RequestBody ProductRequest product) {
        final Product saved = products.add(product.toEntity());
        return ResponseEntity.ok(saved);
    }

    @ResponseBody
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> edit(@PathVariable Long id, @RequestBody ProductRequest request) {
        Product product = products.findById(id).orElseThrow();
        product.update(request.toEntity());
        return ResponseEntity.ok(product);
    }

    @ResponseBody
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Product product = products.findById(id).orElseThrow();
        products.remove(product);
        return ResponseEntity.noContent().build();
    }
}
