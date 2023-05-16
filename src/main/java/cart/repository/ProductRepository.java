package cart.repository;

import cart.domain.Product;
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

  public void update(Product product) {
    String sql = "UPDATE product SET name = ?, price = ?, image = ? WHERE id = ?";
    jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImage(),
        product.getId());
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

  public void save(Product entity) {
    String sql = "INSERT INTO product (name, image, price) VALUES (?, ?, ?)";
    jdbcTemplate.update(sql, entity.getName(), entity.getImage(), entity.getPrice());
  }
}
