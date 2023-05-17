package cart.infra.jdbc;

import cart.domain.entity.Product;
import cart.domain.repository.ProductRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class JdbcProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, getRowMapper(), id));
        } catch (Exception e) {
            throw new NoSuchElementException("해당 상품을 찾을 수 없습니다.");
        }
    }

    @Override
    public void insert(Product product) {
        String sql = "INSERT INTO products (name, price, image_url) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
    }

    @Override
    public Collection<Product> findAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, getRowMapper());
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM products";
        jdbcTemplate.update(sql);
    }

    @Override
    public void update(Long id, Product product) {
        findById(id).orElseThrow(() -> new NoSuchElementException("해당 상품이 존재하지 않습니다."));
        String sql = "UPDATE products SET name = ?, price = ?, image_url = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
    }

    @Override
    public void delete(Long id) {
        findById(id).orElseThrow(() -> new NoSuchElementException("해당 상품이 존재하지 않습니다."));
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Product> getRowMapper() {
        return (rs, rowNum) -> {
            return new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image_url")
            );
        };
    }
}
