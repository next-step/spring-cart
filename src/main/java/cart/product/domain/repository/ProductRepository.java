package cart.product.domain.repository;

import cart.product.domain.entity.Product;

public interface ProductRepository {
    Product insert(Product product);
}
