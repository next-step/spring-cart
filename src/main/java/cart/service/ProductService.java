package cart.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cart.domain.Product;
import cart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update(Product product) {
        return create(product);
    }

    @Transactional
    public Product getProduct(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public List<Product> getList() {
        return productRepository.findAll();
    }
}
