package cart;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductRepository {

  private final JdbcTemplate jdbcTemplate;

  public List<Product> getAll() {
    String sql = "SELECT * FROM product";
    return jdbcTemplate.query(sql, (rs, rowNum) ->
        Product.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .image(rs.getString("image"))
            .price(rs.getBigDecimal("price"))
            .build());
  }
}
