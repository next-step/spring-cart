package cart.persistence;

import cart.product.Product;
import cart.product.ProductEntity;
import cart.product.ProductId;
import cart.product.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProductInMemoryRepository implements ProductRepository {

    private final Map<ProductId, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1L);

    @Override
    public List<ProductEntity> findAll() {
        return products.entrySet()
                .stream()
                .map(entry -> new ProductEntity(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductEntity save(Product product) {
        ProductId productId = new ProductId(idGenerator.getAndIncrement());
        products.put(productId, product);
        return new ProductEntity(productId, product);
    }
}
