package cart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cart.domain.Product;
import cart.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(value = "/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(productService.create(product));
    }

    @DeleteMapping("/product/{id}")
    public String delete(@PathVariable("id") long id) {
        productService.delete(id);
        return "redirect:/product";
    }

    @PutMapping("/product")
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return ResponseEntity.ok().body(productService.update(product));
    }

}
