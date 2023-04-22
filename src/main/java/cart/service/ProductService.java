package cart.service;

import cart.domain.Product;
import cart.domain.Products;
import cart.value.ProductRequest;
import cart.value.ProductResponse;
import cart.value.ProductsResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final Products products;

    public ProductService(Products products) {
        this.products = products;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = products.save(Product.from(productRequest));
        return ProductResponse.from(product);
    }

    public ProductsResponse findAllProducts() {
        return ProductsResponse.from(products.findAll());
    }

    public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
        Product product = products.findById(productId);
        product.update(productRequest);
        return ProductResponse.from(product);
    }
}
