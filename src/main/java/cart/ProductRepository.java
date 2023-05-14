package cart;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductRepository {

  private static Long sequence = 1L;

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

  public void update(ProductUpdateDto updateDto) {
    String sql = "UPDATE product SET name = ?, price = ?, image = ? WHERE id = ?";
    jdbcTemplate.update(sql, updateDto.getName(), updateDto.getPrice(), updateDto.getImage(),
        updateDto.getId());
  }

  public Product findById(Long id) {
    String sql = "SELECT * FROM product WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
        Product.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .image(rs.getString("image"))
            .price(rs.getBigDecimal("price"))
            .build());
  }

  public void deleteById(Long id) {
    String sql = "DELETE FROM product WHERE id = ?";
    jdbcTemplate.update(sql, id);
  }
}
