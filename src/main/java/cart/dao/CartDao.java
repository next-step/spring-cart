package cart.dao;

import cart.domain.Cart;
import cart.dto.AuthInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CartDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertCart;

    public CartDao(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertCart = new SimpleJdbcInsert(dataSource)
                .withTableName("CART")
                .usingGeneratedKeyColumns("id");
    }

    public void insertCart(String email, Cart cart) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", cart.getId());
        parameters.put("email", email);
        parameters.put("product_id", cart.getProductId());
        parameters.put("created_at", LocalDateTime.now());
        insertCart.execute(parameters);
    }

    public void deleteCart(String email, Long id) {
        String sql = "delete from CART where email = ? and id = ?";
        jdbcTemplate.update(sql, email, id);
    }

    public List<Cart> selectCart(String email) {
        String sql = "SELECT c.id ,c.product_id ,  p.name , p.price , p.image_url  FROM CART c ,PRODUCT p where c.product_id = p.id and  c.email = ?";
        return jdbcTemplate.query(
                sql, (rs, rowNum) -> {
                    Cart result = new Cart(
                            rs.getInt("id"),
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getString("image_url")
                    );
                    return result;
                }
                ,email);
    }
}
