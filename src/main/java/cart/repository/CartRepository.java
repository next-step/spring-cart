package cart.repository;

import cart.domain.Cart;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CartRepository {

  private final JdbcTemplate jdbcTemplate;

  public List<Cart> findAll() {
    String sql = "SELECT * FROM CART";
    return jdbcTemplate.query(sql, (((rs, rowNum) ->
        Cart.builder()
            .id(rs.getLong("id"))
            .productId(rs.getLong("productId"))
            .memberId(rs.getLong("memberId"))
            .build())));
  }

  public void addProduct(Long memberId, Long productId) {
    String sql = "INSERT INTO cart (MEMBER_ID, PRODUCT_ID) VALUES (?, ?)";
    jdbcTemplate.update(sql, memberId, productId);
  }

  public List<Cart> findById(Long memberId) {
    String sql = "SELECT * FROM cart WHERE member_id = ?";
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

}
