package cart.product.domain;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
}
