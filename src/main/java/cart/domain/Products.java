package cart.domain;

import cart.controller.dto.ProductResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Products {

    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public List<ProductResponse> CreateProductResponseList(){
        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }
}
