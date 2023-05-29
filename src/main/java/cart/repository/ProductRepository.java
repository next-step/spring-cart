package cart.repository;

import cart.domain.Product;
import java.util.List;
import javax.sql.DataSource;
import org.apache.catalina.realm.DataSourceRealm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert jdbcInsert;

  public ProductRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
    this.jdbcTemplate = jdbcTemplate;
    this.jdbcInsert = new SimpleJdbcInsert(dataSource)
        .withTableName("product")
        .usingGeneratedKeyColumns("id");
  }

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

  public Long update(Product product) {
    String sql = "UPDATE product SET name = ?, image = ?, price = ? WHERE id = ?";

    jdbcTemplate.update(sql, product.getName(), product.getImage(), product.getPrice(), product.getId());
    return product.getId();
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

  public Product save(Product product) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(product);
    long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

    return Product.builder()
        .id(id)
        .name(product.getName())
        .image(product.getImage())
        .price(product.getPrice())
        .build();
  }
}
