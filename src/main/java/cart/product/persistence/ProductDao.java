package cart.product.persistence;

import cart.product.domain.entity.Product;
import cart.product.domain.repository.ProductRepository;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductDao implements ProductRepository {
    public SimpleJdbcInsert insertActor;

    public ProductDao(DataSource dataSource) {
        insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PRODUCT")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Product insert(Product product) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", product.getId());
        parameters.put("name", product.getName());
        parameters.put("price", product.getPrice());
        parameters.put("created_at", LocalDateTime.now());

        Number number = insertActor.executeAndReturnKey(parameters);
        product.setId(number.longValue());
        return product;
    }
}
