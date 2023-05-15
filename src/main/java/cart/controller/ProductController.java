package cart.controller;

import cart.controller.dto.ProductRequest;
import cart.controller.dto.ProductResponse;
import cart.domain.Product;
import cart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String get(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @PostMapping
    public ResponseEntity<ProductResponse> post(@RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.save(productRequest);
        return ResponseEntity.ok().body(response);
    }
}
