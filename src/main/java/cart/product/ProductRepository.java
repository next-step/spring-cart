package cart.product;

import java.util.List;

public interface ProductRepository {

    List<ProductEntity> findAll();

    ProductEntity save(Product product);
}
