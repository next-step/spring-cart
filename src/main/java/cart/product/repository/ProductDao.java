package cart.product.repository;

import cart.product.domain.Product;
import cart.product.dto.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public void insert(ProductRequest productRequest) {
        String SQL = "INSERT INTO PRODUCTS(name, img, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(SQL, productRequest.getName(), productRequest.getImg(), productRequest.getPrice());
    }

    public List<Product> findAll() {
        String SQL = "SELECT * FROM PRODUCTS";
        return jdbcTemplate.query(SQL, (resultSet, rowNum) -> new Product(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("img"), resultSet.getLong("price"), resultSet.getTimestamp("created_at").toLocalDateTime()));
    }

    public void update(Long id, ProductRequest productRequest) {
        String SQL = "UPDATE PRODUCTS SET name = ?, img = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(SQL, productRequest.getName(), productRequest.getImg(), productRequest.getPrice(), id);
    }

    public void delete(Long id) {
        String SQL = "DELETE FROM PRODUCTS WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }
}
