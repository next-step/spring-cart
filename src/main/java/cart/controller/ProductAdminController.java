package cart.controller;

import cart.ProductAdminService;
import cart.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequestMapping("/admin")


public class ProductAdminController {
    private final ProductAdminService productAdminService;

    public ProductAdminController(ProductAdminService adminService) {
        this.productAdminService = adminService;
    }



    @GetMapping("/products")
    public String selectProducts(Model model) {
        model.addAttribute("products", productAdminService.selectAll());
        return "admin";
    }

    @PostMapping("/product")
    public ResponseEntity createProduct(@RequestBody ProductDto product) {
        productAdminService.itemCreate(() -> product.toEntity());

        return ResponseEntity.created(URI.create("/admin")).build();
    }

    @PutMapping("/product")
    public ResponseEntity updateProduct(@RequestBody ProductDto product) {
        productAdminService.itemUpdate(() -> product.toEntity());

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(value = "/product/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Integer productId) {

        productAdminService.itemDelete(productId);

        return ResponseEntity.accepted().build();
    }


}