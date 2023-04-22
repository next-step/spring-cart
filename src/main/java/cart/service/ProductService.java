package cart.service;

import cart.domain.Products;
import cart.value.ProductsResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final Products products;

    public ProductService(Products products) {
        this.products = products;
    }

    public ProductsResponse findAllProducts() {
        return ProductsResponse.from(products.findAll());
    }
}
