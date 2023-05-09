package cart.infrastructure;

import cart.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public Product insert(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(productPreparedStatementCreator(product), keyHolder);

        return Product.builder()
                .id(keyHolder.getKeyAs(Long.class))
                .name(product.getName())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice())
                .build();
    }

    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM product WHERE ID = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, productRowMapper(), id));
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM product";

        return jdbcTemplate.query(sql, productRowMapper());
    }

    private PreparedStatementCreator productPreparedStatementCreator(Product product) {
        String sql = "INSERT INTO product(name, image_url, price) VALUES (?, ?, ?)";

        return connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, product.getName());
            ps.setString(2, product.getImageUrl());
            ps.setInt(3, product.getPrice());
            return ps;
        };
    }

    private RowMapper<Product> productRowMapper() {
        return (resultSet, rowNum) -> Product.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .imageUrl(resultSet.getString("image_url"))
                .price(resultSet.getInt("price"))
                .build();
    }

}
