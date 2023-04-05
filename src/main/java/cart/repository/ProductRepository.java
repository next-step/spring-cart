package cart.repository;

import cart.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll() {
        String sql = "select name, image, price from product";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNumber) -> {
                    Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getString("image"),
                        resultSet.getInt("price")
                    );
                    return product;
                }
        );
    }
}
