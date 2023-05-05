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

        return null;
    }

    @GetMapping("/product/list")
    public List<ProductInfo> readProducts() {

        List<ProductInfo> products = new ArrayList<>();
        products.add(ProductInfo.builder()
                .id(1L)
                .name("치킨")
                .image("/images/chicken.jpeg")
                .price(10000)
                .build());

        products.add(ProductInfo.builder()
                .id(2L)
                .name("샐러드")
                .image("/images/salad.jpeg")
                .price(2000)
                .build());

        products.add(ProductInfo.builder()
                .id(3L)
                .name("피자")
                .image("/images/pizza.jpeg")
                .price(20000)
                .build());

        return products;
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
