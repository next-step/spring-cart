package cart.repository;

import cart.controller.response.MemberResponse;
import cart.domain.Cart;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CartRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public CartRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("CART");
    }

    private final RowMapper<Cart> rowMapper = (resultSet, rowNum)
            -> new Cart(resultSet.getLong("ID"),
            resultSet.getLong("MEMBER_ID"),
            resultSet.getLong("PRODUCT_ID"));

    public List<Cart> findAll(long memberId) {
        final String sql = "SELECT ID, MEMBER_ID, PRODUCT_ID FROM CART WHERE MEMBER_ID = ?";
        return this.jdbcTemplate.query(sql, rowMapper, memberId);
    }

    public void save(Cart cart) {
        jdbcInsert.execute(Cart.getInsertParameter(cart));
    }

    public void deleteById(long memberId, long id) {
        final String deleteQuery = "DELETE FROM CART WHERE ID = ? AND MEMBER_ID = ?";
        jdbcTemplate.update(deleteQuery, id, memberId);
    }

    public void deleteAll(long memberId) {
        final String deleteQuery = "DELETE FROM CART WHERE MEMBER_ID = ?";
        jdbcTemplate.update(deleteQuery, memberId);
    }
}
