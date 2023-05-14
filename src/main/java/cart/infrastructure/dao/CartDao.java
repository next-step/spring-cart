package cart.infrastructure.dao;

import cart.domain.cart.Cart;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CartDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private final RowMapper<Cart> rowMapper;

    public CartDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("cart")
                .usingGeneratedKeyColumns("id");
        this.rowMapper = rowMapper();
    }

    private RowMapper<Cart> rowMapper() {
        return (resultSet, rowNum) -> Cart.builder()
                .id(resultSet.getLong("id"))
                .userId(resultSet.getLong("user_id"))
                .productId(resultSet.getLong("product_id"))
                .build();
    }

    public Cart insert(Cart cart) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(cart);
        Long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return Cart.builder()
                .id(id)
                .userId(cart.getUserId())
                .productId(cart.getProductId())
                .build();
    }

    public Optional<Cart> findById(Long id) {
        String sql = "SELECT * FROM cart WHERE id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    public List<Cart> findAllByUserId(Long userId) {
        String sql = "SELECT * FROM cart WHERE user_id = ?";

        return jdbcTemplate.query(sql, rowMapper, userId);
    }

    public List<Cart> findAllByProductId(Long productId) {
        String sql = "SELECT * FROM cart WHERE product_id = ?";

        return jdbcTemplate.query(sql, rowMapper, productId);
    }

    public Long delete(Long id) {
        String sql = "DELETE FROM cart WHERE id = ?";

        jdbcTemplate.update(sql, id);
        return id;
    }

    public void batchDelete(List<Long> ids) {
        String sql = "DELETE FROM cart WHERE id = ?";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, ids.get(i));
            }

            @Override
            public int getBatchSize() {
                return ids.size();
            }
        });
    }

}
