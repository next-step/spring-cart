package cart.product.domain.repository;

import cart.product.domain.entity.Product;
import cart.product.web.dto.ProductInfo;

import java.util.List;

public interface ProductRepository {
    Product insert(Product product);
    Product findById(Long id);
    List<Product> findAll();
    Product updateProduct(Long id, Product build);
    void delete(Long id);
}
