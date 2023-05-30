package cart.repository;

import cart.domain.Cart;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert cartInsert;

  public CartRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.cartInsert = new SimpleJdbcInsert(jdbcTemplate)
        .withTableName("cart")
        .usingGeneratedKeyColumns("id");
  }

  public void addProduct(Long memberId, Long productId) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("MEMBER_ID", memberId);
    parameters.put("PRODUCT_ID", productId);
    cartInsert.execute(parameters);
  }

  public List<Cart> findById(Long memberId) {
    String sql = "SELECT * FROM cart WHERE MEMBER_ID = ?";
    return jdbcTemplate.query(sql, new Object[]{memberId}, (rs, rowNum) ->
        Cart.builder()
            .id(rs.getLong("id"))
            .productId(rs.getLong("product_id"))
            .memberId(rs.getLong("member_id"))
            .build());
  }

  public void removeCart(Long cartId, Long memberId) {
    String sql = "DELETE FROM cart WHERE id = ? AND MEMBER_ID = ?";
    jdbcTemplate.update(sql, cartId, memberId);
  }

  public List<Cart> findAll() {
    String sql = "SELECT * FROM CART";
    return jdbcTemplate.query(sql, (((rs, rowNum) ->
        Cart.builder()
            .id(rs.getLong("id"))
            .productId(rs.getLong("productId"))
            .memberId(rs.getLong("memberId"))
            .build())));
  }
}
