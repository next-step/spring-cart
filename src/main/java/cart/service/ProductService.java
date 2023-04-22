package cart.service;

import cart.domain.*;
import cart.value.ProductRequest;
import cart.value.ProductResponse;
import cart.value.ProductsResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final Products products;
    private final CartItems cartItems;

    public ProductService(Products products, CartItems cartItems) {
        this.products = products;
        this.cartItems = cartItems;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = products.save(Product.from(productRequest));
        return ProductResponse.from(product);
    }

    public ProductResponse addCartItem(Long productId, Long memberId) {
        Product product = products.findById(productId);
        cartItems.save(new CartItem(productId, memberId));
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

    public void deleteProduct(Long productId) {
        products.deleteById(productId);
    }
}
