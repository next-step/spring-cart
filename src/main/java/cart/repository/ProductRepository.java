package cart.repository;

import cart.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Product> rowMapper = (resultSet, rowNum)
            -> new Product(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("image"), resultSet.getLong("price"));


    public List<Product> findAllProduct() {
        String selectAllProduct = "select id, name, image, price from PRODUCT";
        return this.jdbcTemplate.query(selectAllProduct, this.rowMapper);
    }


}
