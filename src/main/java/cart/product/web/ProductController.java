package cart.product.web;

import cart.product.domain.dto.ProductDto;
import cart.product.domain.service.ProductService;
import cart.product.web.dto.CreateProduct;
import cart.product.web.dto.DeleteProduct;
import cart.product.web.dto.ProductInfo;
import cart.product.web.dto.UpdateProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public CreateProduct.Response createProduct(
            @Valid @RequestBody CreateProduct.Request request) {
        return CreateProduct.Response.from(productService.createProduct(
                request.getName(),
                request.getPrice(),
                request.getImage()));
    }

    @GetMapping("/products/{id}")
    public ProductInfo readProduct(@PathVariable Long id) {
        return ProductInfo.from(productService.getProduct(id));
    }

    @GetMapping("/products")
    public List<ProductInfo> readProducts() {
        return productService.getAllProduct()
                .stream().map(ProductInfo::from)
                .collect(Collectors.toList());
    }

    @PutMapping("/products/{id}")
    public UpdateProduct.Response updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProduct.Request request
    ) {
        return UpdateProduct.Response.from(
                productService.updateProduct(
                        id,
                        ProductDto.builder()
                                .name(request.getName())
                                .image(request.getImage())
                                .price(request.getPrice())
                                .build()));
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(
            @PathVariable Long id
    ) {
        productService.deleteProduct(id);
    }

    @GetMapping("/")
    public ModelAndView showIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", readProducts());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView showAdmin() {
        return new ModelAndView("admin",
                "products", readProducts());
    }
}
