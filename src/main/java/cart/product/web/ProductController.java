package cart.product.web;

import cart.product.domain.service.ProductService;
import cart.product.web.dto.CreateProduct;
import cart.product.web.dto.DeleteProduct;
import cart.product.web.dto.ProductInfo;
import cart.product.web.dto.UpdateProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public ModelAndView showIndexView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("products", readProducts());
        return modelAndView;
    }

    @PostMapping("/product")
    public CreateProduct.Response createProduct(
            @RequestBody CreateProduct.Request request) {

        productService.createProduct(
                request.getProductName(),
                request.getPrice(),
                request.getImagePath());

        return null;
    }

    @GetMapping("/product/{id}")
    public ProductInfo readProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping("/product")
    public List<ProductInfo> readProducts() {
        return productService.getAllProduct();
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
