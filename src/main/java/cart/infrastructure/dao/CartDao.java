package cart.infrastructure.dao;

import cart.domain.cart.Cart;
import cart.domain.product.Product;
import cart.domain.user.User;
import cart.service.cart.exception.ProductDoesNotExistException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

    private final ProductDao productDao;
    private final UsersDao usersDao;

    public CartDao(JdbcTemplate jdbcTemplate, DataSource dataSource, ProductDao productDao, UsersDao usersDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("cart")
                .usingGeneratedKeyColumns("id");
        this.rowMapper = rowMapper();

        this.productDao = productDao;
        this.usersDao = usersDao;
    }

    private RowMapper<Cart> rowMapper() {
        return (resultSet, rowNum) -> {
            User user = usersDao.findById(resultSet.getLong("user_id")).get();
            Product product = productDao.findById(resultSet.getLong("product_id"))
                    .orElseThrow(ProductDoesNotExistException::new);

            return Cart.builder()
                    .id(resultSet.getLong("id"))
                    .user(user)
                    .product(product)
                    .build();
        };
    }

    public Cart insert(Cart cart) {
        SqlParameterSource parameterSource = getSqlParameterSource(cart);
        Long id = jdbcInsert.executeAndReturnKey(parameterSource).longValue();

        return Cart.builder()
                .id(id)
                .user(cart.getUser())
                .product(cart.getProduct())
                .build();
    }

    private SqlParameterSource getSqlParameterSource(Cart cart) {
        return new MapSqlParameterSource()
                .addValue("user_id", cart.getUser().getId())
                .addValue("product_id", cart.getProduct().getId());
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
