package cart.product.web;

import cart.product.web.dto.CreateProduct;
import cart.product.web.dto.DeleteProduct;
import cart.product.web.dto.ProductInfo;
import cart.product.web.dto.UpdateProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ProductController {

    @PostMapping("/product")
    public CreateProduct.Response createProduct(
            @RequestBody CreateProduct.Request request) {

        return null;
    }

    @GetMapping("/product/{id}")
    public ProductInfo readProduct(@PathVariable Long id) {

        return null;
    }

    @GetMapping("/product/list")
    public List<ProductInfo> readProducts() {

        return null;
    }

    @PostMapping("/product/update")
    public UpdateProduct.Response updateProduct(
            @RequestBody UpdateProduct.Request request
            ) {

        return null;
    }

    @PostMapping("/product/delete")
    public DeleteProduct.Response deleteProduct(
            @RequestBody DeleteProduct.Request request
    ) {

        return null;
    }
}
