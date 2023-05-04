package cart.application;

import cart.controller.dto.ProductRequest;
import cart.controller.dto.ProductResponse;
import cart.controller.dto.ProductsResponse;
import cart.domain.Product;
import cart.domain.ProductRepository;
import cart.domain.Products;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductsResponse getAllProducts() {
        Products products = productRepository.findAll();

        return ProductsResponse.of(products);
    }

    public ProductResponse createProduct(ProductRequest request) {
        Product product = request.toProduct();
        productRepository.save(product);

        return ProductResponse.of(product);
    }

}
