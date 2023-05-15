package cart.repository;

import cart.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcProductRepository implements ProductRepository{

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Product> productRowMapper =
            (resultSet, rowNum) -> new Product(resultSet.getString("name"),
                    resultSet.getString("image"),
                    resultSet.getInt("price"));

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from products", productRowMapper);
    }

}
