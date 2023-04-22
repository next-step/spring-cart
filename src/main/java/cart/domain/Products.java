package cart.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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
}
