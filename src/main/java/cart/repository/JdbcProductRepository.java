package cart.repository;

import cart.controller.dto.ProductEditRequest;
import cart.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcProductRepository implements ProductRepository{

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Product> productRowMapper =
            (resultSet, rowNum) -> new Product(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("image"),
                    resultSet.getInt("price"));

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from products", productRowMapper);
    }

    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO products (name, image, price) VALUES (?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, product.getName());
            ps.setString(2, product.getImage());
            ps.setInt(3, product.getPrice());
            return ps;
        }, keyHolder);
        new Product(keyHolder.getKey().longValue());
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "select * from products where id = ?";
        try {
            Product product = jdbcTemplate.queryForObject(sql, productRowMapper, id);
            return Optional.of(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void edit(Long id, Product product) {
        String sql = "update products set name=?, image=?, price=? where id=?";
        jdbcTemplate.update(sql,
                product.getName(),
                product.getImage(),
                product.getPrice(), id);
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from products where id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from products");
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from products", Integer.class);
    }
}
