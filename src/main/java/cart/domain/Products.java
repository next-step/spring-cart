package cart.domain;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class Products {

    private final Map<Long, Product> productContainer = new HashMap<>();
    private final AtomicLong incrementKey = new AtomicLong(0L);

    public Product add(Product product) {
        if (product.getId() == null) {
            product.setId(incrementKey.addAndGet(1L));
        }
        productContainer.put(product.getId(), product);
        return product;
    }

    public List<Product> getAll() {
        return productContainer.values()
            .stream()
            .sorted((p1, p2) -> (int) (p1.getId() - p2.getId()))
            .collect(Collectors.toList());
    }

    public void remove(Product product) {
        if (!productContainer.containsKey(product.getId())) {
            throw new NoSuchElementException();
        }
        productContainer.remove(product.getId());
    }

    public Optional<Product> findById(Long id) {
        if (!productContainer.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.of(productContainer.get(id));
    }
}
