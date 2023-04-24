package cart.domain;

import cart.exception.ProductException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class Products {

    private final List<Product> productContainer = new ArrayList<>();
    private final AtomicLong incrementKey = new AtomicLong(0L);

    public Product add(Product product) {
        if (product.getId() == null) {
            product.setId(incrementKey.incrementAndGet());
            productContainer.add(product);
            return product;
        }
        final Product saved = findById(product.getId()).orElseThrow(() -> new ProductException("sadf"));
        saved.update(product);
        return saved;
    }

    public List<Product> getAll() {
        return productContainer.stream()
            .sorted(Comparator.comparing(Product::getId))
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
