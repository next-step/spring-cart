package cart.dao;

import cart.domain.Cart;
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

    public List<Cart> findCartsByEmail(String email) {
        String sql = "SELECT c.cart_id ,c.product_id ,  p.product_name , p.product_price , p.product_imagename  FROM CART c ,PRODUCT p where c.product_id = p.product_id and  user_email = ?";
        return jdbcTemplate.query(
                sql, (rs, rowNum) -> {
                    Cart result = new Cart(
                            rs.getInt("cart_id"),
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getInt("product_price"),
                            rs.getString("product_imagename")
                    );
                    return result;
                }
                ,email);
    }
}
