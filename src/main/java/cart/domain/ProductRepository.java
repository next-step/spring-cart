package cart.domain;

import java.util.Optional;

public interface ProductRepository {

    Products findAll();

    void save(Product product);

    Optional<Product> findById(long id);

    void update(Product product);

    void delete(Product product);

}
