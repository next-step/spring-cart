package cart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cart.domain.Product;
import cart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product) {
        return create(product);
    }

    public Product find(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getList() {
        return productRepository.findAll();
    }
}
