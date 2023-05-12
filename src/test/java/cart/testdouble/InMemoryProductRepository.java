package cart.testdouble;

import cart.domain.entity.Product;
import cart.domain.repository.ProductRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class InMemoryProductRepository implements ProductRepository {

    private Map<Long, Product> productMap = new HashMap<>();

    @Override
    public Product findById(Long id) {
        Product product = productMap.get(id);
        if (product == null) {
            throw new NoSuchElementException("해당 상품을 찾을 수 없습니다.");
        }
        return product;
    }

    @Override
    public void insert(Product product) {
        productMap.put(product.getId(), product);
    }

    @Override
    public void update(Long id, Product product) {
        productMap.put(id, product);
    }

    @Override
    public void delete(Long id) {
        productMap.remove(id);
    }

    @Override
    public Collection<Product> findAll() {
        return productMap.values();
    }
}
