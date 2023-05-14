package cart.product.repository;

import cart.product.domain.Product;
import cart.product.dto.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public void insert(ProductRequest productRequest) {
        String SQL = "INSERT INTO PRODUCTS(name, img, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(SQL, productRequest.getName(), productRequest.getImg(), productRequest.getPrice());
    }

    private final RowMapper<Product> productsRowMapper = (resultSet, rowNum) -> new Product(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("img"), resultSet.getLong("price"), resultSet.getTimestamp("created_at").toLocalDateTime());
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM PRODUCTS", productsRowMapper);
    }

    public void update(ProductRequest productRequest, Long productId) {
        String SQL = "UPDATE PRODUCTS SET name = ?, img = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(SQL, productRequest.getName(), productRequest.getImg(), productRequest.getPrice(), productId);
    }

    public void delete(Long id) {
        String SQL = "DELETE FROM PRODUCTS WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    public Optional<Product> findById(Long id) {
        String SQL = "SELECT * FROM PRODUCTS WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL, (resultSet, rowNum) -> new Product(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("img"), resultSet.getLong("price"), resultSet.getTimestamp("created_at").toLocalDateTime()), id));
    }
}
