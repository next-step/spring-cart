package cart.infra.jdbc;

import cart.domain.entity.CartItem;
import cart.domain.repository.CartItemRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.NoSuchElementException;

@Repository
public class JdbcCartItemRepository implements CartItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCartItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(CartItem cartItem) {
        String sql = "INSERT INTO cart_items (member_id, product_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, cartItem.getMemberId(), cartItem.getProductId());
    }

    @Override
    public Collection<CartItem> findAllByMemberId(Long memberId) {
        String sql = "SELECT * FROM cart_items WHERE member_id = ?";
        return jdbcTemplate.query(sql, getRowMapper(), memberId);
    }

    @Override
    public CartItem findByCartItem(CartItem cartItem) {
        String sql = "SELECT * FROM cart_items WHERE member_id = ? and product_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, getRowMapper(), cartItem.getMemberId(), cartItem.getProductId());
        } catch (Exception e) {
            throw new NoSuchElementException("카트에서 해당 상품을 찾을 수 없습니다.");
        }
    }

    @Override
    public void delete(CartItem cartItem) {
        CartItem found = findByCartItem(cartItem);
        if (found == null) {
            throw new NoSuchElementException("카트에 해당 상품이 존재하지 않습니다.");
        }
        String sql = "DELETE FROM cart_items WHERE id = ?";
        jdbcTemplate.update(sql, found.getId());
    }

    private RowMapper<CartItem> getRowMapper() {
        return (rs, rowNum) -> {
            return new CartItem(
                    rs.getLong("id"),
                    rs.getLong("member_id"),
                    rs.getLong("product_id")
            );
        };
    }
}
