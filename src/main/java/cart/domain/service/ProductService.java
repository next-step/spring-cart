package cart.domain.service;

import cart.domain.entity.Product;
import cart.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Collection<Product> getAll() {
        return productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.insert(product);
    }

    public void update(Long id, Product product) {
        productRepository.update(id, product);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
