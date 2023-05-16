package cart.domain.repository;

import cart.domain.entity.Product;

import java.util.Collection;

public interface ProductRepository {
    Product findById(Long id);
    void insert(Product product);
    void update(Long id, Product product);
    void delete(Long id);
    Collection<Product> findAll();
    void deleteAll();
}
