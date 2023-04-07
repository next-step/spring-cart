package cart.service;

import cart.domain.Product;
import cart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void setProduct(Product product) {
        productRepository.insert(product);
    }
    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }
}
