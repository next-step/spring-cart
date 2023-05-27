package cart.repository.jdbc;

import cart.domain.Cart;
import cart.domain.Product;
import cart.domain.User;
import cart.exception.JwpCartApplicationException;
import cart.repository.CartRepository;
import cart.repository.ProductRepository;
import cart.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import static cart.exception.ErrorCode.PRODUCT_NOT_FOUND;
import static cart.exception.ErrorCode.USER_NOT_FOUND;

@Repository
public class JdbcCartRepository implements CartRepository {

    private final JdbcTemplate jdbcTemplate;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    public JdbcCartRepository(JdbcTemplate jdbcTemplate, UserRepository userRepository, ProductRepository productRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    private final RowMapper<Cart> cartRowMapper =
            (resultSet, rowNum) -> new Cart(
                    resultSet.getLong("id"),
                    userRepository.findById(resultSet.getLong("user_id"))
                            .orElseThrow(() -> new JwpCartApplicationException(USER_NOT_FOUND)),
                    productRepository.findById(resultSet.getLong("product_id"))
                            .orElseThrow(() -> new JwpCartApplicationException(PRODUCT_NOT_FOUND))
                    );


    @Override
    public Optional<Cart> findById(Long id) {
        String sql = "select * from carts where id = ?";
        try {
            Cart cart = jdbcTemplate.queryForObject(sql, cartRowMapper, id);
            return Optional.of(cart);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Cart> findAllByUserId(Long id) {
        String sql = "select * from carts where user_id = ?";
        return jdbcTemplate.query(sql, cartRowMapper, id);
    }

    @Override
    public Cart add(User user, Product product) {
        String sql = "insert into carts (user_id, product_id) VALUES (?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, user.getId());
            ps.setLong(2, product.getId());
            return ps;
        }, keyHolder);
        return Cart.builder()
                .id(keyHolder.getKey().longValue())
                .user(user)
                .product(product)
                .build();
    }

    @Override
    public void delete(Long cartId) {
        String sql = "delete from carts where id = ?";
        jdbcTemplate.update(sql, cartId);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from carts");
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from carts", Integer.class);
    }
}
