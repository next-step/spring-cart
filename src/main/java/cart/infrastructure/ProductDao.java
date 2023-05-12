package cart.infrastructure;

import cart.domain.product.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private final RowMapper<Product> rowMapper;

    public ProductDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
        this.rowMapper = rowMapper();
    }

    private RowMapper<Product> rowMapper() {
        return (resultSet, rowNum) -> Product.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .imageUrl(resultSet.getString("image_url"))
                .price(resultSet.getInt("price"))
                .build();
    }

    public Product insert(Product product) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(product);
        long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return Product.builder()
                .id(id)
                .name(product.getName())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice())
                .build();
    }

    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM product WHERE ID = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM product";

        return jdbcTemplate.query(sql, rowMapper);
    }

    public Long update(Product product) {
        String sql = "UPDATE product SET name = ?, image_url = ?, price = ? WHERE id = ?";

        jdbcTemplate.update(sql, product.getName(), product.getImageUrl(), product.getPrice(), product.getId());
        return product.getId();
    }

    public Long delete(Product product) {
        String sql = "DELETE FROM product WHERE id = ?";

        jdbcTemplate.update(sql, product.getId());
        return product.getId();
    }

}
