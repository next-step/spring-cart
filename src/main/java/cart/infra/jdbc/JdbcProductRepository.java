package cart.infra.jdbc;

import cart.domain.entity.Product;
import cart.domain.repository.ProductRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class JdbcProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public void insert(Product product) {
        String sql = "INSERT INTO products (name, price, image_url) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
    }

    @Override
    public Collection<Product> findAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image_url")
            );
        });
    }

    @Override
    public void update(Long id, Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, image_url = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
