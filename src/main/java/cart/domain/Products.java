package cart.domain;

import cart.exception.NoSuchProductException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class Products {
    private Long tmpId = 1L;
    private final List<Product> values = new ArrayList<>();

    public Product save(Product product) {
        product.setId(tmpId++);
        values.add(product);
        return product;
    }

    public List<Product> findAll() {
        return values;
    }

    public Product findById(Long productId) {
        return values.stream()
                .filter(it -> Objects.equals(it.getId(), productId))
                .findAny()
                .orElseThrow(NoSuchProductException::new);
    }

    public void deleteById(Long productId) {
        values.removeIf(it -> Objects.equals(it.getId(), productId));
    }
}
