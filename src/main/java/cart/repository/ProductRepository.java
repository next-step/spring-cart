package cart.repository;

import cart.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Product save(Product product);

    Optional<Product> findById(Long id);

    void edit(Long id, Product product);

    void delete(Long id);

    void deleteAll();

    int count();
}
