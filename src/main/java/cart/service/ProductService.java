package cart.service;

import cart.domain.Product;
import cart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
//        return List.of(new Product(1, "상품1", "http://test.test", 2000),
//                new Product(2, "상품2", "http://test.test", 4400),
//                new Product(3, "상품3", "http://test.test", 2200),
//                new Product(4, "상품4", "http://test.test", 7700),
//                new Product(5, "상품5", "http://test.test", 2500));
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }
}
