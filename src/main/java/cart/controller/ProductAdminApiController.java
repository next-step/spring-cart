package cart.controller;

import cart.application.ProductService;
import cart.controller.dto.ProductRequest;
import cart.controller.dto.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author minsukim on 2023/05/07
 * @project jwp-cart
 * @description
 */
@RestController
@RequestMapping("/products")
public class ProductAdminApiController {

    private final ProductService productService;

    public ProductAdminApiController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request){

        System.out.println("test : " + request.toString());
        ProductResponse response = productService.createProduct(request);

        return ResponseEntity.ok(response);
    }
}
