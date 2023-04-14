package cart.controller;

import cart.dto.ProductDto;
import cart.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("products", productService.showProducts());
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("products", productService.showProducts());
        return "admin";
    }

    @PostMapping ("/createProduct")
    public ResponseEntity createProduct(@RequestBody ProductDto product) {
        productService.createProduct(product.toEntity());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/removeProduct")
    public ResponseEntity deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("/changeProduct")
    public ResponseEntity<Integer> updateProduct(@RequestBody ProductDto product) {
        productService.updateProduct(product.toEntity());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
