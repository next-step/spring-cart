package cart.product;

import java.util.List;

public interface ProductRepository {

    List<ProductEntity> findAll();

    ProductEntity findById(Long productId);

    ProductEntity save(Product product);

    void deleteById(Long id);

    ProductEntity update(ProductEntity newProductEntity);
}
