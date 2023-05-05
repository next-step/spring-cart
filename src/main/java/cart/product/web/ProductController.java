package cart.product.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ProductController {

    @PostMapping("/product")
    public String createProduct() {

        return "index";
    }

    @GetMapping("/product/list")
    public String readProducts() {

        return "index";
    }

    @GetMapping("/product/{id}")
    public String readProduct(@PathVariable Long id) {

        return "index";
    }

    @PostMapping("/product/update")
    public String updateProduct() {

        return "index";
    }

    @PostMapping("/product/delete")
    public String deleteProduct() {

        return "index";
    }
}
