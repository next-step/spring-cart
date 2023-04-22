package cart.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Products {
    private Long tmpId;
    private final List<Product> values;

    public Products() {
        this.tmpId = 1L;
        this.values = new ArrayList<>();
    }

    public List<Product> findAll() {
        return values;
    }
}
