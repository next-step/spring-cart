package cart.dao;

import cart.domain.Cart;
import cart.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDAO {
    private JdbcTemplate jdbcTemplate;

    public CartDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertCart(Cart cart) {
        String sql = "insert into CART (user_email, product_id) values (?, ?)";
        return jdbcTemplate.update(sql, cart.getEmail(), cart.getProductId());
    }

    public int deleteCart(Cart cart) {
        String sql = "delete from CART where cart_id = ?";
        return jdbcTemplate.update(sql, cart.getId());
    }

    public List<Cart> selectCarts(Cart cart) {
        String sql = "SELECT cart_id ,product_id FROM CART  where user_email = ? ";
        return jdbcTemplate.query(
                sql, (rs, rowNum) -> {
                    Cart result = new Cart(
                            rs.getInt("cart_id"),
                            rs.getInt("product_id")
                    );
                    return result;
                }
                ,cart.getEmail());
    }
}
