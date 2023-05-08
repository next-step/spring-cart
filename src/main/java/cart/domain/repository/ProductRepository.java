package cart.domain.repository;

import cart.domain.entity.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(int id);
    void insert(Product product);
    List<Product> findAll();
}
