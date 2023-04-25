package cart.persistence;

import cart.product.Product;
import cart.product.ProductId;
import cart.product.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductInMemoryRepository implements ProductRepository {

    private final Map<ProductId, Product> products = new HashMap<>();

    @Override
    public List<Product> findAll() {
        return List.copyOf(products.values());
    }
}
