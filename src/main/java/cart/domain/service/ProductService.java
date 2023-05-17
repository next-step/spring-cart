package cart.domain.service;

import cart.domain.entity.Product;
import cart.domain.repository.CartItemRepository;
import cart.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public ProductService(ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
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
        cartItemRepository.findAllByProductId(id).forEach(cartItemRepository::delete);
        productRepository.delete(id);
    }
}
