package cart.repository;

import cart.domain.Cart;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CartRepository {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public CartRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("CART");
    }

    private final RowMapper<Cart> rowMapper = (resultSet, rowNum)
            -> new Cart(resultSet.getLong("ID"),
            resultSet.getLong("MEMBER_ID"),
            resultSet.getLong("PRODUCT_ID"));

    public List<Cart> findAll(long memberId) {
        String sql = "SELECT ID, MEMBER_ID, PRODUCT_ID FROM CART WHERE MEMBER_ID = ?";
        return this.jdbcTemplate.query(sql, rowMapper, memberId);
    }

    public void save(Cart cart) {
        jdbcInsert.execute(Cart.getInsertParameter(cart));
    }

    public void deleteById(long id) {
        String deleteQuery = "DELETE FROM CART WHERE ID = ?";
        jdbcTemplate.update(deleteQuery, id);
    }
}
