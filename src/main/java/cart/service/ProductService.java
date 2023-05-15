package cart.service;

import cart.controller.dto.ProductRequest;
import cart.controller.dto.ProductResponse;
import cart.domain.Product;
import cart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public ProductResponse save(ProductRequest pr) {
        Product product = productRepository.save(Product.of(pr.getName(), pr.getImage(), pr.getPrice()));
        return ProductResponse.of(product);
    }
}
