package cart.repository;

import cart.controller.dto.ProductEditRequest;
import cart.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Product save(Product product);

    Optional<Product> findById(Long id);

    void edit(Long id, ProductEditRequest productEditRequest);

    void delete(Long id);
}
