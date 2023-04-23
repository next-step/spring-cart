package cart.repository;

import cart.domain.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductRepository {
    private final Map<Long, ProductEntity> products = new HashMap<>();
    private final AtomicLong incrementId = new AtomicLong(1);

    public Long save(ProductEntity product) {
        long id = incrementId.getAndIncrement();
        product.setId(id);
        this.products.put(id, product);
        return id;
    }

    public void update(ProductEntity productEntity) {
        ProductEntity product = this.products.get(productEntity.getId());
        if (product == null) {
            throw new IllegalArgumentException(
                    String.format("수정하고자 하는 상품 데이터가 존재하지 않습니다. productId: %d, productName: %s",
                            productEntity.getId(), productEntity.getName()));
        }
        this.products.put(productEntity.getId(), productEntity);
    }

    public boolean deleteById(Long id) {
        return this.products.remove(id) != null;
    }

    public List<ProductEntity> findAll() {
        return new ArrayList<>(this.products.values());
    }
}
