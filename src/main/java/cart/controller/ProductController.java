package cart.controller;

import cart.domain.Product;
import cart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product/create")
    ResponseEntity insertProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/product/delete")
    ResponseEntity deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/product/update")
    ResponseEntity updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return new ResponseEntity(HttpStatus.OK);
    }
}
