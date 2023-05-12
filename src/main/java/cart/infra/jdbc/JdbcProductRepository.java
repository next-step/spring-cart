package cart.infra.jdbc;

import cart.domain.entity.Product;
import cart.domain.repository.ProductRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.NoSuchElementException;

@Repository
public class JdbcProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product findById(Long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
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
        if (findById(id) == null) {
            throw new NoSuchElementException("해당 상품이 존재하지 않습니다.");
        }
        String sql = "UPDATE products SET name = ?, price = ?, image_url = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
    }

    @Override
    public void delete(Long id) {
        if (findById(id) == null) {
            throw new NoSuchElementException("해당 상품이 존재하지 않습니다.");
        }
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
