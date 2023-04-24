package cart.repository;

import cart.domain.Cart;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {
    private final JdbcTemplate jdbcTemplate;

    public CartRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void addCart(Cart cart) {
        String sql = "INSERT INTO CART(member_id, product_id) VALUES(?, ?)";
        jdbcTemplate.update(
                    sql,
                    cart.getMemberId(),
                    cart.getProductId()
                );
    }
}
