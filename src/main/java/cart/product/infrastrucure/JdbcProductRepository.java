package cart.product.infrastrucure;

import cart.product.domain.Product;
import cart.product.domain.ProductRepository;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcProductRepository implements ProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcProductRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT id, name, image, price FROM product";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    private final RowMapper<Product> productRowMapper = (rs, rowNum) ->
        new Product(rs.getLong("id"),
            rs.getString("name"),
            rs.getString("image"),
            rs.getInt("price"));
}
