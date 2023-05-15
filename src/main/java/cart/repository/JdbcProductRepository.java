package cart.repository;

import cart.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
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
        product.setId(keyHolder.getKey().longValue());
        return product;
    }

}
