package cart.controller.dto;

import cart.domain.Product;
import cart.domain.Products;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsResponse {

    private List<ProductResponse> products;

    private ProductsResponse(List<ProductResponse> products) {
        this.products = products;
    }

    public static ProductsResponse of(Products products) {
        List<Product> productsValue = products.getValue();
        List<ProductResponse> productResponses = productsValue.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());

        return new ProductsResponse(productResponses);
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

}
