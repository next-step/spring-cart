package cart.product.web;

import cart.product.domain.dto.ProductDto;
import cart.product.domain.service.ProductService;
import cart.product.web.dto.CreateProduct;
import cart.product.web.dto.DeleteProduct;
import cart.product.web.dto.ProductInfo;
import cart.product.web.dto.UpdateProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductApiController {

    private final ProductService productService;

    @PostMapping("/product")
    public CreateProduct.Response createProduct(
            @RequestBody CreateProduct.Request request) {
        return CreateProduct.Response.from(productService.createProduct(
                request.getName(),
                request.getPrice(),
                request.getImage()));
    }

    @GetMapping("/product/{id}")
    public ProductInfo readProduct(@PathVariable Long id) {
        return ProductInfo.from(productService.getProduct(id));
    }

    @GetMapping("/product")
    public List<ProductInfo> readProducts() {
        return productService.getAllProduct()
                .stream().map(ProductInfo::from)
                .collect(Collectors.toList());
    }

    @PostMapping("/product/{id}")
    public UpdateProduct.Response updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProduct.Request request
    ) {
        productService.updateProduct(
                id,
                ProductDto.builder()
                        .name(request.getName())
                        .image(request.getImage())
                        .price(request.getPrice())
                        .build());
        return null;
    }

    @PostMapping("/product/delete")
    public DeleteProduct.Response deleteProduct(
            @RequestBody DeleteProduct.Request request
    ) {
        productService.deleteProduct(request.getId());
        return null;
    }
}
