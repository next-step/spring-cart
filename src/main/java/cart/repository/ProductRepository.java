package cart.repository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductRepository {
    private final List<ProductEntity> products = new ArrayList<>();
    private final AtomicLong incrementId = new AtomicLong(0);

    public Long save(ProductEntity product) {
        long id = incrementId.getAndIncrement();
        product.setId(id);
        this.products.add(product);
        return id;
    }

    public List<ProductEntity> findAll() {
        return this.products;
    }
}
