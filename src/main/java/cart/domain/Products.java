package cart.domain;

import cart.exception.ProductException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class Products {

    private final List<Product> productContainer = new ArrayList<>();
    private final AtomicLong incrementKey = new AtomicLong(0L);

    public Product add(Product product) {
        if (product.getId() == null) {
            product.setId(incrementKey.addAndGet(1L));
            productContainer.add(product);
            return product;
        }
        final Product saved = findById(product.getId()).orElseThrow(() -> new ProductException("sadf"));
        saved.update(product);
        return saved;
    }

    public List<Product> getAll() {
        return productContainer.stream()
            .sorted((p1, p2) -> (int) (p1.getId() - p2.getId()))
            .collect(Collectors.toList());
    }

    public void remove(Product product) {
        productContainer.remove(product);
    }

    public Optional<Product> findById(Long id) {
        return productContainer.stream()
            .filter(product -> product.getId().equals(id))
            .findAny();
    }
}
