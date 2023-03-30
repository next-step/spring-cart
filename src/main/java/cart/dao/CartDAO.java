package cart.dao;

import cart.model.Cart;
import cart.model.Carts;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAO {

    private final RowMapper<Cart> actorRowMapper = (resultSet, rowNum) -> {
        Cart cart = new Cart(
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getInt("product_id")
        );
        return cart;
    };
    private final RowMapper<Cart> actorRowMapperFullCart = (resultSet, rowNum) -> {
        Cart cart = new Cart(
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getInt("product_id"),
            resultSet.getString("name"),
            resultSet.getString("image"),
            resultSet.getInt("price")
        );
        return cart;
    };
    private JdbcTemplate jdbcTemplate;

    public CartDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Carts list(String email, String password) {
        String sql = "select a.email, a.password, a.product_id, b.id, b.name, b.image, b.price from cart a, product b where a.product_id = b.id and a.email = ? and a.password = ?";
        Carts carts = new Carts(jdbcTemplate.query(sql, actorRowMapperFullCart, email, password));
        return carts;
    }

    public int insertCart(Cart cart) {
        String sql = "insert into cart (email, password, product_id) values (?,?,?)";
        return this.jdbcTemplate.update(sql, cart.getEmail(), cart.getPassword(),
            cart.getProductId());
    }

    public Cart getCart(String email, String password) {
        String sql = "select * from cart where email = ? and password = ?";
        Cart cart = jdbcTemplate.queryForObject(sql, actorRowMapper, email, password);
        return cart;
    }

    public int deleteCart(Cart cart) {
        String sql = "delete from cart where email = ? and password = ? and product_id = ?";
        return this.jdbcTemplate.update(sql, cart.getEmail(), cart.getPassword(),
            cart.getProductId());
    }
}
