package cart.cart.infra;

import cart.cart.domain.Cart;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRowMapper implements RowMapper<Cart> {

    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Cart.builder()
                .id(rs.getLong("id"))
                .memberId(rs.getLong("member_id"))
                .productId(rs.getLong("product_id"))
                .build();
    }

}
