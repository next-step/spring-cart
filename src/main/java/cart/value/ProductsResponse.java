package cart.value;

import cart.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsResponse {
    private final List<ProductResponse> products;

    private ProductsResponse(List<ProductResponse> products) {
        this.products = products;
    }

    public static ProductsResponse from(List<Product> products) {
        return new ProductsResponse(products.stream()
                .map(ProductResponse::from)
                .collect(Collectors.toList()));
    }

    public List<ProductResponse> getProducts() {
        return products;
    }
}
